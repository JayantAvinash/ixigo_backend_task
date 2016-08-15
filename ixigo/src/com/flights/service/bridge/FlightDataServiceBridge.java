package com.flights.service.bridge;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.flights.beans.Airport;
import com.flights.beans.FlightSearch;
import com.flights.beans.TravelDetailsObject;
import com.flights.service.impl.FlightDataServiceImpl;

/**
 * Web Service Bridge which will return the data for all the custom requests in
 * front end.
 *
 */
@Path("/Flights/")
public class FlightDataServiceBridge extends FlightDataServiceImpl {

	// Gives default airports when search page opens for placeholder. can take
	// user location as input to provide him
	// more customized default airport locations
	@GET
	@Produces("application/json")
	@Path("defaultSearch")
	public Response getDefaultSearchPage() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.ok(mapper.writeValueAsString(super.getDefaultSearchData())).build();
	}

	/*
	 * Gives the list of airports on clicking 'departure' and 'arrival airports'
	 */
	@GET
	@Produces("application/json")
	@Path("default")
	public List<Airport> getDefaultAirports() {
		return super.getDefaultAirports();
	}

	/*
	 * Gives the list of airports when a user searches after getting an ajax
	 * call from frontend
	 */
	@GET
	@Produces("application/json")
	@Path("airportSearch/{name}")
	public List<Airport> getAirportSearchResults(@PathParam("name") String name) {
		return super.searchAirports(name);
	}

	/*
	 * Gets all the flights data based on user's search sorted by price
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("flightData")
	public String getAvailableFlights(FlightSearch flightSearch) throws JsonGenerationException, JsonMappingException, IOException {
		List<TravelDetailsObject> travelOptions = new ArrayList<TravelDetailsObject>();
		Map<String, TravelDetailsObject> optimumTravelDetails = super.getFlightsData(flightSearch);
		for (String flightCode : optimumTravelDetails.keySet()) {
			travelOptions.add(optimumTravelDetails.get(flightCode));
		}
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		mapper.setDateFormat(df);
		return mapper.writeValueAsString(travelOptions);
		//return travelOptions;
	}
}
