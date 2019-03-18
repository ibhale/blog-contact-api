package com.blog.contact.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.contact.exception.InvalidFilterException;
import com.blog.contact.model.Account;
import com.blog.contact.model.ContactInfo;
import com.blog.contact.service.ContactInfoService;
import com.blog.contact.validations.Validator;

import lombok.extern.slf4j.Slf4j;

/*
 * Main controller class, it has four methods for CRUD operations wrt contact information
 * */
@RestController
@RequestMapping("/profile")
@Slf4j
public class ContactInfoController {
	@Autowired
	ContactInfoService service;
	/*
	 * fetches record, added validations for phone number, similarly validations filter for email, state , city can be added
	 * @param email
	 * @param state
	 * @param city
	 * @param phoneNumber
	 * @returns ResponseEntity object
	 *
	 */
	@GetMapping("/contact")
	public ResponseEntity<List<ContactInfo>> get(@RequestParam(value = "email", required = false) String email,
			String state, String city, String phoneNumber) {
		if(!Validator.validatePhoneNumber(phoneNumber))			
			throw new InvalidFilterException(phoneNumber);
		log.info("attempt to retrieve the record");
		return new ResponseEntity<List<ContactInfo>>(service.getRecord(email, phoneNumber, city, state), HttpStatus.OK);
	}

	/*
	 * creates new record
	 * @body input account object
	 * @throw IOException
	 * @returns ResponseEntity object
	 */
	@PostMapping("/contact")
	@ResponseBody
	public ResponseEntity<String> create(@RequestBody Account input) throws IOException {
		log.info("attempt to create a record");
		return new ResponseEntity<String>(service.createRecord(input), HttpStatus.OK);
	}
	
	
	/*
	 * updates record
	 * @param userName
	 * @param email
	 * @returns ResponseEntity object
	 * 
	 */
	@RequestMapping(value = "/contact/{userName}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("userName") String userName, String email) {
		log.info("attempt to update a record");
		return new ResponseEntity<String>(service.updateRecord(userName, email), HttpStatus.OK);

	}

	/*
	 * deletes record
	 * @param email
	 * @param phoneNumber
	 * @param userName
	 * @returns ResponseEntity object
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam(value = "email", required = false) String email,
			String phoneNumber, String userName) {
		log.info("attempt to delete a record");
		return new ResponseEntity<String>(service.deleteRecord(userName, null, null), HttpStatus.OK);
	}

}
