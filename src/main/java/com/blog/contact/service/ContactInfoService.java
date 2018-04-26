package com.blog.contact.service;

import java.io.IOException;
import java.util.List;

import com.blog.contact.model.Account;
import com.blog.contact.model.ContactInfo;

public interface ContactInfoService {
	public List<ContactInfo> getRecord(String email, String phoneNumber, String city, String state);

	public String createRecord(Account input) throws IOException;

	public String updateRecord(String userName, String email);

	public String deleteRecord(String userName, String email, String phoneNumber);
}
