package com.blog.contact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Phone object, which hold all the user phone number information
 * */
@Entity
public class Phone {
	
	@Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account account;
	public Phone() {
		super();
	}
	private String number;
	public Phone(Account account, String number, String type) {
		super();
		this.account = account;
		this.number = number;
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public Account getAccount() {
		return account;
	}
	private String type;


}