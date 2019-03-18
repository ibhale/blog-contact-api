package com.blog.contact.service;

import java.io.IOException;
import java.util.List;

import com.blog.contact.model.Account;
import com.blog.contact.model.ContactInfo;

public interface ContactInfoService {
	
	/*
	 * get record
	 * @param email
	 * @param phoneNumber
	 * @param city
	 * @param state
	 * @returns List<ContactInfo>
	 */
	public List<ContactInfo> getRecord(String email, String phoneNumber, String city, String state);

	/*
	 * create record
	 * @body input
	 * @throws IOException
	 * @returns String
	 */
	public String createRecord(Account input) throws IOException;

	/*
	 * update record
	 * @param email
	 * @param userName
	 * @returns String
	 */
	public String updateRecord(String userName, String email);
	
	/*
	 * delete record
	 * @param email
	 * @param userName
	 * @param phoneNumber
	 * @returns String
	 */
	public String deleteRecord(String userName, String email, String phoneNumber);
}
