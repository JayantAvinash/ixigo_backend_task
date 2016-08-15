package com.flights.beans;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Bean to send flight details for all service providers
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class ServiceProviderDetails {

	private String name;
	private double price;
	private String offers;
	private double infantPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public double getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(double infantPrice) {
		this.infantPrice = infantPrice;
	}

}
