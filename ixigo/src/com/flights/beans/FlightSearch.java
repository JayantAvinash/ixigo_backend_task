package com.flights.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 * Bean populated by Json obtained on clicking 'Search Flights' button.
 * Sent to service providers to get respective flight details
 * */
public class FlightSearch {

	private Airport source;
	private Airport destination;
	private Date travelDate;
	private Date returnDate;
	private HashMap<String, Integer> noOfGuests;
	private List<String> seatClasses;
	private String selectedClass;

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

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public HashMap<String, Integer> getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(HashMap<String, Integer> noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public List<String> getSeatClasses() {
		return seatClasses;
	}

	public void setSeatClasses(List<String> seatClasses) {
		this.seatClasses = seatClasses;
	}

	public String getSelectedClass() {
		return selectedClass;
	}

	public void setSelectedClass(String selectedClass) {
		this.selectedClass = selectedClass;
	}

}
