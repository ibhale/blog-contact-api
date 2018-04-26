package com.blog.contact.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/*
 * Account domain object, which hold all the user information
 * */
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String company;
	private String email;
	private String bithdate;
	@OneToOne(mappedBy = "account")
	private Image pic;
	@OneToMany(mappedBy = "account")
	private Set<Phone> number = new HashSet<>();
	@OneToOne(mappedBy = "account")
	private Address address;

	public Account() {
		super();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setBithdate(String bithdate) {
		this.bithdate = bithdate;
	}

	public Account(String userName, String company, String email, String bithdate) {
		super();
		this.userName = userName;
		this.company = company;
		this.email = email;
		this.bithdate = bithdate;

	}

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBithdate() {
		return bithdate;
	}

	public Set<Phone> getNumber() {
		return number;
	}

	public void setNumber(Set<Phone> number) {
		this.number = number;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

}