package com.flights.service.inf;

import java.util.List;
import java.util.Map;

import com.flights.beans.Airport;
import com.flights.beans.FlightSearch;
import com.flights.beans.TravelDetailsObject;

/**
 * An interface encapsulated with list of functions for all the actions by user
 * at front end
 *
 */
public interface FlightDataServiceInf {

	// Gives the default airports for placeholder in search page.
	public FlightSearch getDefaultSearchData();

	/*
	 * Returns a list of most famous airports as options when user starts
	 * selecting arrival and departure airport
	 */
	public List<Airport> getDefaultAirports();

	/*
	 * Returns a list of airports as options based on the search string entered
	 * by user
	 */
	public List<Airport> searchAirports(String name);

	/*
	 * Returns all the available flights based on data provided by all the
	 * service providers
	 */
	public Map<String, TravelDetailsObject> getFlightsData(FlightSearch flightSearch);
}
