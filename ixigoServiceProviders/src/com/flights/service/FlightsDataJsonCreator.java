package com.flights.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flights.beans.Airport;
import com.flights.beans.FlightDetailsObject;
import com.flights.beans.FlightSearch;
import com.flights.beans.TravelDetailsObject;

public class FlightsDataJsonCreator {

	public Map<String, TravelDetailsObject> getFlightsDataForMMT(FlightSearch flightSearch) {
		Map<String, TravelDetailsObject> mmtFlights = new HashMap<String, TravelDetailsObject>();
		if (flightSearch.getReturnDate() == null) {
			Map<List<String>, TravelDetailsObject> tdObjOneWay = createOneWayNonStopMMTFlight(flightSearch);
			for (List<String> key : tdObjOneWay.keySet())
				mmtFlights.put(key.toString(), tdObjOneWay.get(key));
			//tdObj.setFlightDetails(flightDetails);
			Map<List<String>, TravelDetailsObject> tdObjOneWayOneStop = createOneWayOneStopMMTFlight(flightSearch);
			for (List<String> key : tdObjOneWayOneStop.keySet())
				mmtFlights.put(key.toString(), tdObjOneWayOneStop.get(key));
		} else {
			Map<List<String>, TravelDetailsObject> tdObjReturn = createReturnMMTFlight(flightSearch);
			for (List<String> key : tdObjReturn.keySet())
				mmtFlights.put(key.toString(), tdObjReturn.get(key));
		}
		return mmtFlights;
	}

	private Map<List<String>, TravelDetailsObject> createOneWayOneStopMMTFlight(FlightSearch flightSearch) {
		TravelDetailsObject tdObj = new TravelDetailsObject();
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("G8");
		onwardFlight.setFlightNo(101);
		tdObj.setInfantCost(1000);
		onwardFlight.setNoOfStops(1);
		tdObj.setOfferDetails("500 cashback");
		tdObj.setPrice(8000);
		tdObj.setSeatClass("Economy");
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("8:00PM");
		onwardFlight.setDuration(330);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		Airport stop = new Airport();
		stop.setCity("Bengaluru");
		stop.setCountry("India");
		stop.setId("BLR");
		stop.setName("KempeGowda International Airport");
		FlightDetailsObject beforeStop = new FlightDetailsObject();
		beforeStop.setAirlineCode("G8");
		beforeStop.setFlightNo(101);
		beforeStop.setSource(flightSearch.getSource());
		beforeStop.setDestination(stop);
		beforeStop.setTakeOffTime("2:30PM");
		beforeStop.setLandingTime("4:40PM");
		beforeStop.setDuration(130);
		beforeStop.setTravelDate(flightSearch.getTravelDate());
		
		FlightDetailsObject afterStop = new FlightDetailsObject();
		afterStop.setAirlineCode("JA");
		afterStop.setFlightNo(101);
		afterStop.setSource(flightSearch.getSource());
		afterStop.setDestination(stop);
		afterStop.setTakeOffTime("6:00PM");
		afterStop.setLandingTime("8:00PM");
		afterStop.setDuration(120);
		afterStop.setTravelDate(flightSearch.getTravelDate());
		
		List<FlightDetailsObject> connectingFlights = new ArrayList<FlightDetailsObject>();
		connectingFlights.add(beforeStop);
		connectingFlights.add(afterStop);
		
		onwardFlight.setConnectingFlights(connectingFlights);
		
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(false);
		List<String> key = new ArrayList<String>();
		key.add(onwardFlight.getAirlineCode() + onwardFlight.getFlightNo());
		key.add(afterStop.getAirlineCode() + afterStop.getFlightNo());
		Map<List<String>, TravelDetailsObject> oneWayFlight = new HashMap<List<String>, TravelDetailsObject>();
		oneWayFlight.put(key, tdObj);
		return oneWayFlight;
	}
	
	private Map<List<String>, TravelDetailsObject> createReturnMMTFlight(FlightSearch flightSearch) {
		TravelDetailsObject tdObj = new TravelDetailsObject();
		tdObj.setOfferDetails("500 cashback");
		tdObj.setPrice(5000);
		tdObj.setSeatClass("Economy");
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("G8");
		onwardFlight.setFlightNo(100);
		tdObj.setInfantCost(1000);
		onwardFlight.setNoOfStops(0);
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("4:40PM");
		onwardFlight.setDuration(130);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		
		FlightDetailsObject returnFlight = new FlightDetailsObject();
		returnFlight.setAirlineCode("AI");
		returnFlight.setFlightNo(100);
		returnFlight.setNoOfStops(0);
		returnFlight.setTakeOffTime("2:30PM");
		returnFlight.setLandingTime("4:40PM");
		returnFlight.setDuration(130);
		returnFlight.setSource(flightSearch.getDestination());
		returnFlight.setDestination(flightSearch.getSource());
		returnFlight.setTravelDate(flightSearch.getReturnDate());
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		toAndFroFlights.put("returnJourney", returnFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(true);
		List<String> key = new ArrayList<String>();
		key.add(onwardFlight.getAirlineCode() + onwardFlight.getFlightNo());
		key.add(returnFlight.getAirlineCode() + returnFlight.getFlightNo());
		Map<List<String>, TravelDetailsObject> returnJourney = new HashMap<List<String>, TravelDetailsObject>();
		returnJourney.put(key, tdObj);
		return returnJourney;
	}
	
	private Map<List<String>, TravelDetailsObject> createOneWayNonStopMMTFlight(FlightSearch flightSearch) {
		TravelDetailsObject tdObj = new TravelDetailsObject();
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("G8");
		onwardFlight.setFlightNo(100);
		tdObj.setInfantCost(1000);
		onwardFlight.setNoOfStops(0);
		tdObj.setOfferDetails("500 cashback");
		tdObj.setPrice(5000);
		tdObj.setSeatClass("Economy");
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("4:40PM");
		onwardFlight.setDuration(130);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(false);
		List<String> key = new ArrayList<String>();
		key.add(onwardFlight.getAirlineCode() + onwardFlight.getFlightNo());
		Map<List<String>, TravelDetailsObject> oneWayFlight = new HashMap<List<String>, TravelDetailsObject>();
		oneWayFlight.put(key, tdObj);
		return oneWayFlight;
	}
	
	public Map<String, TravelDetailsObject> getFlightsDataForMusafir(FlightSearch flightSearch) {
		Map<String, TravelDetailsObject> musFlights = new HashMap<String, TravelDetailsObject>();
		if (flightSearch.getReturnDate() == null) {
			Map<List<String>, TravelDetailsObject> tdObjOneWay = createOneWayNonStopMusFlight(flightSearch);
			for (List<String> key : tdObjOneWay.keySet())
				musFlights.put(key.toString(), tdObjOneWay.get(key));
			//tdObj.setFlightDetails(flightDetails);
			/*Map<List<String>, TravelDetailsObject> tdObjOneWayOneStop = createOneWayOneStopMMTFlight(flightSearch);
			for (List<String> key : tdObjOneWayOneStop.keySet())
				mmtFlights.put(key.toString(), tdObjOneWayOneStop.get(key));*/
		} else {
			/*Map<List<String>, TravelDetailsObject> tdObjReturn = createReturnMMTFlight(flightSearch);
			for (List<String> key : tdObjReturn.keySet())
				mmtFlights.put(key.toString(), tdObjReturn.get(key));*/
		}
		return musFlights;
	}
	
	public Map<List<String>, TravelDetailsObject> createOneWayNonStopMusFlight(FlightSearch flightSearch) {
		Map<List<String>, TravelDetailsObject> mFlights = new HashMap<List<String>, TravelDetailsObject>();
		TravelDetailsObject tdObj = new TravelDetailsObject();
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("G8");
		onwardFlight.setFlightNo(100);
		onwardFlight.setInfantCost(1000);
		onwardFlight.setNoOfStops(0);
		//tdObj.setOfferDetails("500 cashback");
		tdObj.setPrice(4700);
		tdObj.setSeatClass("Economy");
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("4:40PM");
		onwardFlight.setDuration(130);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(false);
		List<String> key = new ArrayList<String>();
		key.add("G8100");
		mFlights.put(key, tdObj);
		//tdObj.setFlightDetails(flightDetails);
		return mFlights;
	}
	
	public Map<String, TravelDetailsObject> getFlightsDataForCT(FlightSearch flightSearch) {
		Map<String, TravelDetailsObject> ctFlights = new HashMap<String, TravelDetailsObject>();
		if (flightSearch.getReturnDate() == null) {
			Map<List<String>, TravelDetailsObject> tdObjOneWay = createOneWayNonStopCTFlight(flightSearch);
			for (List<String> key : tdObjOneWay.keySet())
				ctFlights.put(key.toString(), tdObjOneWay.get(key));
			//tdObj.setFlightDetails(flightDetails);
			/*Map<List<String>, TravelDetailsObject> tdObjOneWayOneStop = createOneWayOneStopMMTFlight(flightSearch);
			for (List<String> key : tdObjOneWayOneStop.keySet())
				mmtFlights.put(key.toString(), tdObjOneWayOneStop.get(key));*/
		} else {
			/*Map<List<String>, TravelDetailsObject> tdObjReturn = createReturnMMTFlight(flightSearch);
			for (List<String> key : tdObjReturn.keySet())
				mmtFlights.put(key.toString(), tdObjReturn.get(key));*/
		}
		return ctFlights;
	}
	
	public Map<List<String>, TravelDetailsObject> createOneWayNonStopCTFlight(FlightSearch flightSearch) {
		Map<List<String>, TravelDetailsObject> ctFlights = new HashMap<List<String>, TravelDetailsObject>();
		TravelDetailsObject tdObj = new TravelDetailsObject();
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("JA");
		onwardFlight.setFlightNo(100);
		onwardFlight.setInfantCost(1000);
		onwardFlight.setNoOfStops(0);
		tdObj.setOfferDetails("400 cashback");
		tdObj.setPrice(4800);
		tdObj.setSeatClass("Economy");
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("4:40PM");
		onwardFlight.setDuration(130);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(false);
		List<String> key = new ArrayList<String>();
		key.add("JA100");
		ctFlights.put(key, tdObj);
		//tdObj.setFlightDetails(flightDetails);
		return ctFlights;
	}
	
	public Map<String, TravelDetailsObject> getFlightsDataForEMT(FlightSearch flightSearch) {
		Map<String, TravelDetailsObject> emtFlights = new HashMap<String, TravelDetailsObject>();
		if (flightSearch.getReturnDate() == null) {
			Map<List<String>, TravelDetailsObject> tdObjOneWay = createOneWayNonStopEMTFlight(flightSearch);
			for (List<String> key : tdObjOneWay.keySet())
				emtFlights.put(key.toString(), tdObjOneWay.get(key));
			//tdObj.setFlightDetails(flightDetails);
			/*Map<List<String>, TravelDetailsObject> tdObjOneWayOneStop = createOneWayOneStopMMTFlight(flightSearch);
			for (List<String> key : tdObjOneWayOneStop.keySet())
				mmtFlights.put(key.toString(), tdObjOneWayOneStop.get(key));*/
		} else {
			/*Map<List<String>, TravelDetailsObject> tdObjReturn = createReturnMMTFlight(flightSearch);
			for (List<String> key : tdObjReturn.keySet())
				mmtFlights.put(key.toString(), tdObjReturn.get(key));*/
		}
		return emtFlights;
	}
	
	public Map<List<String>, TravelDetailsObject> createOneWayNonStopEMTFlight(FlightSearch flightSearch) {
		Map<List<String>, TravelDetailsObject> emtFlights = new HashMap<List<String>, TravelDetailsObject>();
		TravelDetailsObject tdObj = new TravelDetailsObject();
		FlightDetailsObject onwardFlight = new FlightDetailsObject();
		onwardFlight.setAirlineCode("JA");
		onwardFlight.setFlightNo(100);
		onwardFlight.setInfantCost(1000);
		onwardFlight.setNoOfStops(0);
		tdObj.setOfferDetails("600 cashback");
		tdObj.setPrice(4600);
		tdObj.setSeatClass("Economy");
		onwardFlight.setTakeOffTime("2:30PM");
		onwardFlight.setLandingTime("4:40PM");
		onwardFlight.setDuration(130);
		onwardFlight.setSource(flightSearch.getSource());
		onwardFlight.setDestination(flightSearch.getDestination());
		onwardFlight.setTravelDate(flightSearch.getTravelDate());
		Map<String, FlightDetailsObject> toAndFroFlights = new HashMap<String, FlightDetailsObject>();
		toAndFroFlights.put("onwardJourney", onwardFlight);
		tdObj.setToAndFroFlights(toAndFroFlights);
		tdObj.setReturnFlight(false);
		List<String> key = new ArrayList<String>();
		key.add("JA100");
		emtFlights.put(key, tdObj);
		//tdObj.setFlightDetails(flightDetails);
		return emtFlights;
	}
	
	

}
