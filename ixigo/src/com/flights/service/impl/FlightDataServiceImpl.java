package com.flights.service.impl;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.flights.beans.Airport;
import com.flights.beans.FlightSearch;
import com.flights.beans.ServiceProviderDetails;
import com.flights.beans.TravelDetailsObject;
import com.flights.constants.WebConstant.ServiceProviders;
import com.flights.dao.AirportDaoObject;
import com.flights.service.inf.FlightDataServiceInf;

public class FlightDataServiceImpl implements FlightDataServiceInf {

	public static final String DEFAULT_SRC_AIRPORT_ID = "DEL";
	public static final String DEFAULT_DEST_AIRPORT_ID = "MUM";

	/*
	 * Gives the default airports for placeholder in search page. Right now
	 * hard-coded to Delhi and Mumbai. Can customize it based on user's previous
	 * searches, his location, etc
	 */
	@Override
	public FlightSearch getDefaultSearchData() {
		FlightSearch defDetails = new FlightSearch();
		Airport source = AirportDaoObject.getAirportById(DEFAULT_SRC_AIRPORT_ID);
		Airport dest = AirportDaoObject.getAirportById(DEFAULT_DEST_AIRPORT_ID);
		defDetails.setSource(source);
		defDetails.setDestination(dest);
		return defDetails;
	}

	/*
	 * Returns a list of most famous airports as options when user starts
	 * selecting arrival and departure airport
	 */
	@Override
	public List<Airport> getDefaultAirports() {
		List<Airport> airports = AirportDaoObject.getDefaultAirports();
		return airports;
	}

	/*
	 * Returns a list of airports as options based on the search string entered
	 * by user
	 */
	@Override
	public List<Airport> searchAirports(String name) {
		List<Airport> airports = AirportDaoObject.searchAirports(name);
		return airports;
	}

	/*
	 * Returns all the available flights based on data provided by all the
	 * service providers
	 */
	@Override
	public Map<String, TravelDetailsObject> getFlightsData(FlightSearch flightSearch) {
		Map<String, HashMap<String, TravelDetailsObject>> providerData = new HashMap<String, HashMap<String, TravelDetailsObject>>();
		/*
		 * Getting data from all the service providers synchronously as of now.
		 * For faster response, we may have to make asynchronous web service calls and sending data 
		 * to front end based on their availability
		 * */
		for (ServiceProviders s : ServiceProviders.values()) {
			try {
				HashMap<String, TravelDetailsObject> tdObj = getFlightDetailsFromServiceProvider(s.getUrl(), flightSearch);
				if (tdObj != null) {
					providerData.put(s.name(), tdObj);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return getOptimumFlightData(providerData);
	}

	/*
	 * Optimizes the flight data by merging the data provided by all the service
	 * providers
	 */
	private Map<String, TravelDetailsObject> getOptimumFlightData(
			Map<String, HashMap<String, TravelDetailsObject>> providerData) {
		Map<String, TravelDetailsObject> flightDataMap = new HashMap<String, TravelDetailsObject>();
		for (String provider : providerData.keySet()) {
			if (flightDataMap.isEmpty()) {
				flightDataMap = providerData.get(provider);
				for (String flightCode : flightDataMap.keySet()) {
					TravelDetailsObject tdObj = flightDataMap.get(flightCode);
					ServiceProviderDetails spd = new ServiceProviderDetails();
					spd.setName(provider);
					spd.setPrice(tdObj.getPrice());
					spd.setInfantPrice(tdObj.getInfantCost());
					spd.setOffers(tdObj.getOfferDetails());
					Map<String, ServiceProviderDetails> providerDetails = new HashMap<String, ServiceProviderDetails>();
					providerDetails.put(provider, spd);
					tdObj.setServiceProviderPrice(providerDetails);
				}
			} else {
				Map<String, TravelDetailsObject> providerTravelDetails = providerData.get(provider);
				for (String flightCode : providerTravelDetails.keySet()) {
					TravelDetailsObject providerTdObj = providerTravelDetails.get(flightCode);
					if (flightDataMap.containsKey(flightCode)) {
						TravelDetailsObject tdObj = flightDataMap.get(flightCode);
						if (tdObj.getPrice() > providerTdObj.getPrice()) {
							tdObj.setPrice(providerTdObj.getPrice());
							tdObj.setOfferDetails(providerTdObj.getOfferDetails());
						}
						ServiceProviderDetails spd = new ServiceProviderDetails();
						spd.setName(provider);
						spd.setPrice(providerTdObj.getPrice());
						spd.setInfantPrice(providerTdObj.getInfantCost());
						spd.setOffers(providerTdObj.getOfferDetails());
						Map<String, ServiceProviderDetails> providerDetails = tdObj.getServiceProviderPrice();
						providerDetails.put(provider, spd);
						// tdObj.setServiceProviderPrice(providerDetails);
					} else {
						ServiceProviderDetails spd = new ServiceProviderDetails();
						spd.setName(provider);
						spd.setPrice(providerTdObj.getPrice());
						spd.setInfantPrice(providerTdObj.getInfantCost());
						spd.setOffers(providerTdObj.getOfferDetails());
						Map<String, ServiceProviderDetails> providerDetails = new HashMap<String, ServiceProviderDetails>();
						providerDetails.put(provider, spd);
						providerTdObj.setServiceProviderPrice(providerDetails);
						flightDataMap.put(flightCode, providerTdObj);
					}
				}
			}
		}
		if (!flightDataMap.isEmpty()) {
			TreeMap<String, TravelDetailsObject> sortedFlightDataMap = new TreeMap<String, TravelDetailsObject>(
					new PriceComparator(flightDataMap));
			sortedFlightDataMap.putAll(flightDataMap);
			return sortedFlightDataMap;
		}
		return flightDataMap;

	}

	/*
	 * Price comparator for sorting the data for all the flight plans based on
	 * price
	 */
	class PriceComparator implements Comparator<String> {
		private Map<String, TravelDetailsObject> travelDetailsObjMap;

		public PriceComparator(Map<String, TravelDetailsObject> map) {
			this.travelDetailsObjMap = map;
		}

		@Override
		public int compare(String o1, String o2) {
			if (travelDetailsObjMap.get(o1).getPrice() > travelDetailsObjMap.get(o2).getPrice()) {
				return 1;
			} else if (travelDetailsObjMap.get(o1).getPrice() == travelDetailsObjMap.get(o2).getPrice()) {
				return 0;
			} else {
				return -1;
			}
		}

	}

	/*
	 * Returns flight data provided by service providers; currently returning
	 * static json data by making rest call to webservice hosted on another
	 * server
	 */
	private HashMap<String, TravelDetailsObject> getFlightDetailsFromServiceProvider(String url,
			FlightSearch flightSearch) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(url);
		postRequest.addHeader("content-type", "application/json");
		ObjectMapper objMapper = new ObjectMapper();
		StringEntity str = new StringEntity(objMapper.writeValueAsString(flightSearch));
		postRequest.setEntity(str);
		HttpResponse response = httpClient.execute(postRequest);
		HttpEntity entity = response.getEntity();

		String responseBody = EntityUtils.toString(entity);
		EntityUtils.consume(entity);

		HashMap<String, TravelDetailsObject> tdObj = objMapper.readValue(responseBody,
				new TypeReference<HashMap<String, TravelDetailsObject>>() {
				});
		return tdObj;
	}

}
