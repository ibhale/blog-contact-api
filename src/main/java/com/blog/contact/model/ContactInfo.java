package com.blog.contact.model;

import java.util.HashSet;
import java.util.Set;
/*
 * Contact info object, which will be part of response object, properties will be used by UI
 * */
public class ContactInfo{
	private Long id;
	private String userName;
	private String company;
	private String email;
	private String bithdate;
	private Set<Phone> number = new HashSet<>();
	private Address address;
	byte[] image;
	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getCompany() {
		return company;
	}
	public String getEmail() {
		return email;
	}
	public String getBithdate() {
		return bithdate;
	}
	public Set<Phone> getNumber() {
		return number;
	}
	public Address getAddress() {
		return address;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBithdate(String bithdate) {
		this.bithdate = bithdate;
	}
	public void setNumber(Set<Phone> number) {
		this.number = number;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ContactInfo(Long id, String userName, String company, String email, String bithdate, Set<Phone> number,
			Address address, byte[] image) {
		super();
		this.id = id;
		this.userName = userName;
		this.company = company;
		this.email = email;
		this.bithdate = bithdate;
		this.number = number;
		this.address = address;
		this.image = image;
	}
}
