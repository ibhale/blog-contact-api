package com.solstice.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.blog.contact.ContactApiApplication;
import com.blog.contact.controller.ContactInfoController;
import com.blog.contact.service.ContactInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApiApplication.class)
@WebAppConfiguration
public class ContactInfoControllerTest {
	public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	private MockMvc mockMvc;
	@MockBean
	ContactInfoService service;
	@InjectMocks
	ContactInfoController testController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(testController).build();

	}

	@Test
	public void getRecord_When_ValidRequest1() throws Exception{
		final ResultActions result = mockMvc.perform(get("/profile/contact").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		System.out.println(result);
	}
	@Test
	public void getRecord_When_ValidRequest2() throws Exception{
		final ResultActions result = mockMvc.perform(get("/profile/contact?email=abc1@gmail.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		System.out.println(result);
	}
	@Test
	public void getRecord_When_ValidRequest3() throws Exception{
		final ResultActions result = mockMvc.perform(get("/profile/contact?city=Wheeling").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		System.out.println(result);
	}
	@Test
	public void getRecord_When_ValidRequest4() throws Exception{
		final ResultActions result = mockMvc.perform(get("/profile/contact?state=Illinois").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		System.out.println(result);
	}
	
	
}
