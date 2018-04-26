package com.solstice.challenge.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blog.contact.ContactApiApplication;
import com.blog.contact.model.Account;
import com.blog.contact.model.Address;
import com.blog.contact.model.ContactInfo;
import com.blog.contact.model.Phone;
import com.blog.contact.service.ContactInfoService;
import com.blog.contact.service.ContactInfoServiceImpl;
import com.blog.contact.util.ContactInfoServiceUtility;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApiApplication.class)
@WebAppConfiguration
public class ContactInfoServiceImplTest {
	@InjectMocks
	ContactInfoServiceImpl impl;
	@Autowired
	ContactInfoService service;
	@MockBean
	ContactInfoServiceUtility utility;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.standaloneSetup(impl).build();
	}

	/*
	 * test case, When a valid email filter value is passed
	 */
	@Test
	public void testGetRecordImpl1() throws Exception {
		List<Account> accounts = new ArrayList<>();
		List<ContactInfo> users1 = new ArrayList<>();
		Account account = new Account();

		account.setCompany("solstice");
		account.setEmail("abc@gmail.com");
		account.setBithdate("11/11/1990");
		account.setUserName("xyz");
		Set<Phone> phones = new HashSet<>();
		Phone phone = new Phone(account, "1234567890", "work");
		phones.add(phone);
		account.setNumber(phones);

		account.setAddress(new Address(account, "street", "city", "state"));
		accounts.add(account);
		ContactInfo user1 = new ContactInfo(account.getId(), account.getUserName(), account.getCompany(),
				account.getEmail(), account.getBithdate(), account.getNumber(), account.getAddress(), null);
		users1.add(user1);
		Mockito.when(utility.searchByEmail("abc@gmail.com")).thenReturn(accounts);
		Mockito.when(utility.createContactInfoList(accounts)).thenReturn(users1);
		List<ContactInfo> users2 = service.getRecord("abc@gmail.com", null, null, null);
		assertEquals(users2.get(0).getEmail(), "abc@gmail.com");
	}

	/*
	 * test case, When no filter is passed, so it will return all available profiles
	 */
	@Test
	public void testGetRecordImpl2() throws Exception {
		List<Account> accounts = new ArrayList<>();
		List<ContactInfo> users1 = new ArrayList<>();
		Account account = new Account();

		account.setCompany("solstice");
		account.setEmail("abc@gmail.com");
		account.setBithdate("11/11/1990");
		account.setUserName("xyz");
		Set<Phone> phones = new HashSet<>();
		Phone phone = new Phone(account, "1234567890", "work");
		phones.add(phone);
		account.setNumber(phones);

		account.setAddress(new Address(account, "street", "city", "state"));
		accounts.add(account);
		ContactInfo user1 = new ContactInfo(account.getId(), account.getUserName(), account.getCompany(),
				account.getEmail(), account.getBithdate(), account.getNumber(), account.getAddress(), null);
		users1.add(user1);
		Mockito.when(utility.searchAll()).thenReturn(accounts);
		Mockito.when(utility.createContactInfoList(accounts)).thenReturn(users1);
		List<ContactInfo> users2 = service.getRecord(null, null, null, null);
		assertEquals(users2.get(0).getEmail(), "abc@gmail.com");
	}
	/*
	 * negative test case, When a valid email filter value is passed but record with that email is not available, so it should return size 0
	 */
	@Test
	public void testGetRecordImpl3() throws Exception {
		List<Account> accounts = new ArrayList<>();
		List<ContactInfo> users1 = new ArrayList<>();
		Account account = new Account();

		account.setCompany("solstice");
		account.setEmail("abc@gmail.com");
		account.setBithdate("11/11/1990");
		account.setUserName("xyz");
		Set<Phone> phones = new HashSet<>();
		Phone phone = new Phone(account, "1234567890", "work");
		phones.add(phone);
		account.setNumber(phones);

		account.setAddress(new Address(account, "street", "city", "state"));
		accounts.add(account);
		ContactInfo user1 = new ContactInfo(account.getId(), account.getUserName(), account.getCompany(),
				account.getEmail(), account.getBithdate(), account.getNumber(), account.getAddress(), null);
		users1.add(user1);
		Mockito.when(utility.searchAll()).thenReturn(accounts);
		Mockito.when(utility.createContactInfoList(accounts)).thenReturn(users1);
		List<ContactInfo> users2 = service.getRecord("abc1@gmail.com", null, null, null);
		assertEquals(users2.size(), 0);
	}
}
