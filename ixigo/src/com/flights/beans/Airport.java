package com.flights.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*Bean class encapsulating details of an airport and is also hibernate entity for airport_details table in city.
 * Id is the primary key and name of the airport and city should be unique*/
@Entity
@Table(name = "ixg_airport_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "city" }) })
public class Airport {

	private String id;
	private String name;
	private String city;
	private String country;

	public Airport() {

	}

	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
