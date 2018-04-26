package com.blog.contact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Image object, which hold all the profile picture information
 * */
@Entity
public class Image {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@JsonIgnore
	@OneToOne
	private Account account;
	private String type;
	@Lob
    private byte[] pic;
	private String name;
	private String location;
	public Image() {
		super();
	}
	public Image(Account account, String type, byte[] pic, String name) {
		super();
		this.account = account;
		this.type = type;
		this.pic = pic;
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
