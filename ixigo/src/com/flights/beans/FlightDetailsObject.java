package com.flights.beans;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Class encapsulating all the details of a flight.
 *	Field names are self explanatory
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class FlightDetailsObject {
	
	private Airport source;
	private Airport destination;
	private String takeOffTime;
	private String landingTime;
	private int duration;
	private String airlineCode;
	private long flightNo;
	// If flight is not direct, then details of connecting flight.
	private List<FlightDetailsObject> connectingFlights;
	private double price;
	private int noOfStops;
	private String offerDetails;
	private double infantCost;
	private Date travelDate;
	
	public Airport getSource() {
		return source;
	}
	public void setSource(Airport source) {
		this.source = source;
	}
	public Airport getDestination() {
		return destination;
	}
	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	public String getTakeOffTime() {
		return takeOffTime;
	}
	public void setTakeOffTime(String takeOffTime) {
		this.takeOffTime = takeOffTime;
	}
	public String getLandingTime() {
		return landingTime;
	}
	public void setLandingTime(String landingTime) {
		this.landingTime = landingTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAirlineCode() {
		return airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	public long getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(long flightNo) {
		this.flightNo = flightNo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNoOfStops() {
		return noOfStops;
	}
	public void setNoOfStops(int noOfStops) {
		this.noOfStops = noOfStops;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	public double getInfantCost() {
		return infantCost;
	}
	public void setInfantCost(double infantCost) {
		this.infantCost = infantCost;
	}
	public List<FlightDetailsObject> getConnectingFlights() {
		return connectingFlights;
	}
	public void setConnectingFlights(List<FlightDetailsObject> connectingFlights) {
		this.connectingFlights = connectingFlights;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date date) {
		this.travelDate = date;
	}
	
}
