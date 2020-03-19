package com.group3.courseenrollment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(max=45)
	private String street;
	@NotBlank
	@Size(max=45)
	private String city;
	@NotBlank
	@Size(min=5, max=5)
	private String postalCode;
	@NotBlank
	@Size(max=45)
	private String country;

	public Address(String street, String city, String postalCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public Address() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
