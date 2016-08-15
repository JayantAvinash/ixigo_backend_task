package com.flights.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.flights.beans.Airport;
import com.flights.beans.FlightSearch;

public class FlightSearchData {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		getTravelPlans();
		//getDefaultAirports();
		//getAirportSearchResults();
		//getDefaultSearchPage();
	}
	
	
	//get prominent airports for user while selecting airport for arrival and departure
	private static void getDefaultAirports() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8081/ixigo/api/flightService/Flights/default");
		getRequest.addHeader("content-type", "application/json");
		HttpResponse response = httpClient.execute(getRequest);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		System.out.println(responseBody);
	}
	
	/*
	 * Gives the list of airports based on user search
	 *
	 */
	private static void getAirportSearchResults() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8081/ixigo/api/flightService/Flights/airportSearch/Del");
		getRequest.addHeader("content-type", "application/json");
		HttpResponse response = httpClient.execute(getRequest);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		System.out.println(responseBody);
	}
	
	// for getting placeholder values of airport when search page is loaded
	private static void getDefaultSearchPage() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8081/ixigo/api/flightService/Flights/defaultSearch");
		getRequest.addHeader("content-type", "application/json");
		HttpResponse response = httpClient.execute(getRequest);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		System.out.println(responseBody);
	}
	
	//Gets all the flights data based on user's search sorted by price
	private static void getTravelPlans() throws JsonGenerationException, JsonMappingException, UnsupportedEncodingException, IOException {
		FlightSearch flightSearchData = createFlightSearchJson();
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost("http://localhost:8081/ixigo/api/flightService/Flights/flightData");
		postRequest.addHeader("content-type", "application/json");
		ObjectMapper objMapper = new ObjectMapper();
		StringEntity str = new StringEntity(objMapper.writeValueAsString(flightSearchData));
		postRequest.setEntity(str);
		HttpResponse response = httpClient.execute(postRequest);
		HttpEntity entity = response.getEntity();

		String responseBody = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		System.out.println(responseBody);
	}
	
	//creating one way flight search object used by getTravelPlans() method
	private static FlightSearch createFlightSearchJson() {
		FlightSearch fs = new FlightSearch();
		Airport source = new Airport();
		source.setCity("New Delhi");
		source.setCountry("India");
		source.setId("DEL");
		source.setName("IGI Airport");
		Airport dest = new Airport();
		dest.setCity("Mumbai");
		dest.setCountry("India");
		dest.setId("MUM");
		dest.setName("Chhatrapati Shivaji International Airport");
		fs.setSource(source);
		fs.setDestination(dest);
		fs.setTravelDate(new Date());
		fs.setSelectedClass("Economy");
		HashMap<String, Integer> noOfGuests = new HashMap<String, Integer>();
		noOfGuests.put("Adult", 2);
		noOfGuests.put("Children", 2);
		noOfGuests.put("Infant", 1);
		fs.setNoOfGuests(noOfGuests);
		return fs;
	}
	
	
	//creating return flight search object used by getTravelPlans() method
	private static FlightSearch createFlightSearchReturnJson() {
		FlightSearch fs = new FlightSearch();
		Airport source = new Airport();
		source.setCity("New Delhi");
		source.setCountry("India");
		source.setId("DEL");
		source.setName("IGI Airport");
		Airport dest = new Airport();
		dest.setCity("Mumbai");
		dest.setCountry("India");
		dest.setId("MUM");
		dest.setName("Chhatrapati Shivaji International Airport");
		fs.setSource(source);
		fs.setDestination(dest);
		fs.setTravelDate(new Date());
		fs.setReturnDate(new Date());
		fs.setSelectedClass("Economy");
		HashMap<String, Integer> noOfGuests = new HashMap<String, Integer>();
		noOfGuests.put("Adult", 2);
		noOfGuests.put("Children", 2);
		noOfGuests.put("Infant", 1);
		fs.setNoOfGuests(noOfGuests);
		return fs;
	}

}
