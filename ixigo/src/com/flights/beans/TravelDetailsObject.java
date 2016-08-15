package com.flights.beans;

import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Class explaining travel details for a particular search. Customer chooses his
 * preferred travel plan from a list of travel details object. Fields which are
 * not self-explanatory are explained below
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class TravelDetailsObject {
	// Contains flight details of onward journey as well as return journey(if
	// opted for)
	private Map<String, FlightDetailsObject> toAndFroFlights;
	// minimum price for the travel plan from prices provided by all service
	// providers
	private double price;
	// Economy, Business, etc
	private String seatClass;
	// Offer for the service provider having minimum price
	private String offerDetails;
	private boolean isReturnFlight;
	// Pricing and offer details of service providers individually(customer can
	// book from any of the service provider)
	private Map<String, ServiceProviderDetails> serviceProviderPrice;
	// Price for infant
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

	public Map<String, ServiceProviderDetails> getServiceProviderPrice() {
		return serviceProviderPrice;
	}

	public void setServiceProviderPrice(Map<String, ServiceProviderDetails> serviceProviderPrice) {
		this.serviceProviderPrice = serviceProviderPrice;
	}

	public double getInfantCost() {
		return infantCost;
	}

	public void setInfantCost(double infantCost) {
		this.infantCost = infantCost;
	}

}
