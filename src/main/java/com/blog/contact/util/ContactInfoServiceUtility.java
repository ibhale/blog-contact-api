package com.blog.contact.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.blog.contact.exception.UserNotFoundException;
import com.blog.contact.model.Account;
import com.blog.contact.model.Address;
import com.blog.contact.model.ContactInfo;
import com.blog.contact.model.Phone;
import com.blog.contact.persistence.AccountRepository;
import com.blog.contact.persistence.AddressRepository;
import com.blog.contact.persistence.PhoneRepository;
/*
 * Utility class, this class contains reusable utility methods to avoid redundant code
 * */
@Component
public class ContactInfoServiceUtility {
	private final AccountRepository accountRepository;
	private final PhoneRepository phoneRepository;
	private final AddressRepository addressRepository;
	List<Account> accounts = null;

	
	
	@Autowired
	ContactInfoServiceUtility(AccountRepository accountRepository, PhoneRepository phoneRepository,
			AddressRepository addressRepository) {
		this.accountRepository = accountRepository;
		this.phoneRepository = phoneRepository;
		this.addressRepository = addressRepository;
	}
/*
 * transform image from a location to byte array, which is further loaded into DB
 * */
	public byte[] convertImagetoByteArray(String location) throws IOException {
		ClassPathResource image = new ClassPathResource(location);
		byte[] arrayPic = new byte[(int) image.contentLength()];
		image.getInputStream().read(arrayPic);
		return arrayPic;
	}
	/*
	 * fetches all available profiles when no filter passed
	 * */
	public List<Account> searchAll() {

		return this.accountRepository.findAll();
	}
	/*
	 * fetches all available profiles matched by a username
	 * */
	public List<Account> searchByUserName(String userName) {
		accounts = new ArrayList<>();
		accounts.add(this.accountRepository.findByUserName(userName));
		if(accounts.get(0)==null)
			throw new UserNotFoundException(userName);
		return accounts;
	}
	/*
	 * fetches all available profiles matched by a email
	 * */
	public List<Account> searchByEmail(String email) {
		accounts = new ArrayList<>();
		accounts.add(this.accountRepository.findByEmail(email));
		if(accounts.get(0)==null)
			throw new UserNotFoundException(email);
		return accounts;
	}
	/*
	 * fetches all available profiles matched by a phonenumber
	 * */
	public List<Account> searchByPhoneNumber(String phoneNumber) {
		accounts = new ArrayList<>();
		List<Phone> phones = this.phoneRepository.findAll();
		for (Phone phone : phones) {
			if (phoneNumber.equals(phone.getNumber())) {

				accounts.add(phone.getAccount());
				break;
			}
		}
		if(accounts.get(0)==null)
			throw new UserNotFoundException(phoneNumber);
		return accounts;
	}
	/*
	 * fetches all available profiles matched by a city or state
	 * */

	public List<Account> searchByAddress(String city, String state) {
		accounts = new ArrayList<>();
		List<Address> addresses = this.addressRepository.findAll();
		for (Address address : addresses) {
			if (state.equals(address.getState()) || city.equals(address.getCity())) {
				accounts.add(address.getAccount());
			}
		}
		if(accounts.get(0)==null)
			throw new UserNotFoundException(city+", "+state);
		return accounts;
	}
	/*
	 *convert list of account object to list of contact info object
	 * */

	public List<ContactInfo> createContactInfoList(List<Account> accounts) {
		List<ContactInfo> users = new ArrayList<>();
		ContactInfo user = null;
		for (Account account : accounts) {
			user = new ContactInfo(account.getId(), account.getUserName(), account.getCompany(), account.getEmail(),
					account.getBithdate(), account.getNumber(), account.getAddress(), account.getPic().getPic());
			users.add(user);
		}
		return users;
	}
}
