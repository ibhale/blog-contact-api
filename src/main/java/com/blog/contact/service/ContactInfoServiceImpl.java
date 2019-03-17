package com.blog.contact.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.contact.model.Account;
import com.blog.contact.model.Address;
import com.blog.contact.model.ContactInfo;
import com.blog.contact.model.Image;
import com.blog.contact.model.Phone;
import com.blog.contact.persistence.AccountRepository;
import com.blog.contact.persistence.AddressRepository;
import com.blog.contact.persistence.ImageRepository;
import com.blog.contact.persistence.PhoneRepository;
import com.blog.contact.util.ContactInfoServiceUtility;

/*
 * Service layer that holds decision logic to call appropriate subsystem, here subsystem could be a call to another api or database.
 * */
@Service
public class ContactInfoServiceImpl implements ContactInfoService {
	private final AccountRepository accountRepository;
	private final PhoneRepository phoneRepository;
	private final AddressRepository addressRepository;
	private final ImageRepository imageRepository;

	@Autowired
	ContactInfoServiceUtility serviceUtility;

	@Autowired
	ContactInfoServiceImpl(AccountRepository accountRepository, PhoneRepository phoneRepository,
			AddressRepository addressRepository, ImageRepository imageRepository) {
		this.accountRepository = accountRepository;
		this.phoneRepository = phoneRepository;
		this.addressRepository = addressRepository;
		this.imageRepository = imageRepository;
	}

	/*
	 * Search a record based on filters like email, phonenumber, city, state
	 */
	@Override
	public List<ContactInfo> getRecord(String email, String phoneNumber, String city, String state) {
		if (email != null && !("").equals(email.trim())) {
			return serviceUtility.createContactInfoList(serviceUtility.searchByEmail(email));
		} else if (phoneNumber != null && !("").equals(phoneNumber.trim())) {
			return serviceUtility.createContactInfoList(serviceUtility.searchByPhoneNumber(phoneNumber));
		} else if ((city != null && !("").equals(city.trim())) || (state != null && !("").equals(state.trim()))) {
			return serviceUtility.createContactInfoList(serviceUtility.searchByAddress(city, state));
		} else {
			return serviceUtility.createContactInfoList(serviceUtility.searchAll());
		}
	}

	/*
	 * create a new record
	 */
	@Override
	public String createRecord(Account input) throws IOException {

		Account account = this.accountRepository
				.save(new Account(input.getUserName(), input.getCompany(), input.getEmail(), input.getBithdate()));
		/*
		 * this.imageRepository.save(new Image(account, input.getPic().getType(),
		 * serviceUtility.convertImagetoByteArray(input.getPic().getLocation()),
		 * input.getPic().getName()));
		 */	for (Phone phone : input.getNumber()) {
			this.phoneRepository.save(new Phone(account, phone.getNumber(), phone.getType()));
		}
		Address address = input.getAddress();
		this.addressRepository.save(new Address(account, address.getStreet(), address.getCity(), address.getState()));
		return "Contact Record Created";
	}

	/*
	 * update a new record, currently we update only email, while search an user
	 * using by username only. In the future, can be searched by username, date of
	 * birth, etc. Similarly, ability to update phone number or any other field in
	 * not provided due to time constraints.
	 */
	@Override
	public String updateRecord(String userName, String email) {
		List<Account> accounts = serviceUtility.searchByUserName(userName);

		for (Account account : accounts) {

			if (email != null)
				account.setEmail(email);

			this.accountRepository.save(account);
		}

		return "Contact Record Updated";
	}

	/*
	 * In delete we are sequentially deleting repositories one by one, if one
	 * operation failed, then it should revert back all previous deleted
	 * table records for that particular call. Its not handled in this api, given
	 * time constraints. It will result in many issues, like null pointer exception
	 * for the cases, like address is deleted successfully but lets say phone number
	 * is not deleted for a particular record, and we try to delete that record again.
	 */
	@Override
	public String deleteRecord(String userName, String email, String phoneNumber) {
		List<Account> accounts = null;
		if (userName != null && !("").equals(userName.trim())) {
			accounts = serviceUtility.searchByUserName(userName);
		} else if (email != null && !("").equals(email.trim())) {
			accounts = serviceUtility.searchByEmail(email);
		} else if (phoneNumber != null && !("").equals(phoneNumber.trim())) {
			accounts = serviceUtility.searchByPhoneNumber(phoneNumber);
		} else {
			accounts = serviceUtility.searchAll();
		}
		for (Account account : accounts) {
			this.addressRepository.delete(account.getAddress());
			this.imageRepository.delete(account.getPic());
			for (Phone phone : account.getNumber()) {
				this.phoneRepository.delete(phone);
			}

			this.accountRepository.delete(account);
		}
		return "Contact Record Deleted";
	}

}
