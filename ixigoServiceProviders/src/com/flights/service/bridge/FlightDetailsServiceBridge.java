package com.flights.service.bridge;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.flights.beans.FlightSearch;
import com.flights.beans.TravelDetailsObject;
import com.flights.service.FlightsDataJsonCreator;

@Path("/FlightDetails/")
public class FlightDetailsServiceBridge extends FlightsDataJsonCreator{
	
	@POST
	@Consumes("application/json")
	@Produces ("application/json")
	@Path("mmt")
	public Map<String, TravelDetailsObject> getAvailableFlightsForMMT(FlightSearch flightSearch) {
		return super.getFlightsDataForMMT(flightSearch);
	}
	
	@POST
	@Consumes("application/json")
	@Produces ("application/json")
	@Path("ct")
	public Map<String, TravelDetailsObject> getAvailableFlightsForCT(FlightSearch flightSearch) {
		return super.getFlightsDataForCT(flightSearch);
	}
	
	@POST
	@Consumes("application/json")
	@Produces ("application/json")
	@Path("emt")
	public Map<String, TravelDetailsObject> getAvailableFlightsForEMT(FlightSearch flightSearch) {
		return super.getFlightsDataForEMT(flightSearch);
	}
	
	@POST
	@Consumes("application/json")
	@Produces ("application/json")
	@Path("mus")
	public Map<String, TravelDetailsObject> getAvailableFlightsForMusafir(FlightSearch flightSearch) {
		return super.getFlightsDataForMusafir(flightSearch);
	}
	
	@POST
	@Consumes("application/json")
	@Produces ("application/json")
	@Path("test")
	public String test() {
		return "hi";
	}

}
