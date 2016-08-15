package com.flights.beans;

import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class TravelDetailsObject {
	
	private Map<String, FlightDetailsObject> toAndFroFlights;
	private double price;
	private String seatClass;
	private String offerDetails;
	private boolean isReturnFlight;
	private double infantCost;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	public Map<String, FlightDetailsObject> getToAndFroFlights() {
		return toAndFroFlights;
	}
	public void setToAndFroFlights(Map<String, FlightDetailsObject> toAndFroFlights) {
		this.toAndFroFlights = toAndFroFlights;
	}
	public boolean isReturnFlight() {
		return isReturnFlight;
	}
	public void setReturnFlight(boolean isReturnFlight) {
		this.isReturnFlight = isReturnFlight;
	}
	public double getInfantCost() {
		return infantCost;
	}
	public void setInfantCost(double infantCost) {
		this.infantCost = infantCost;
	}
	
}
