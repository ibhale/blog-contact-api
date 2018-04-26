package com.blog.contact.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Address object, which hold all the user address information
 * */
@Entity
public class Address {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@JsonIgnore
	@OneToOne
	private Account account;
	private String street;
	private String state;
	private String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public Address() {
		super();
	}

	public Address(Account account, String street, String city, String state) {
		super();
		this.account = account;
		this.street = street;
		this.state = state;
		this.city = city;
	}
}
