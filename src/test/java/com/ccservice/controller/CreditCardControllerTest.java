package com.ccservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.ccservice.exception.CCExceptionControllerAdvice;
import com.ccservice.exception.InvalidCardException;
import com.ccservice.service.CreditCardService;
import com.ccservice.vo.CreditCardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@PropertySource("classpath:message.properties")
public class CreditCardControllerTest {
	@MockBean
	private CreditCardService creditCardservice;
	private MockMvc mockMvc;

	@Mock
	Environment env;

	@Autowired
	private CreditCardController controller;

	@InjectMocks
	CCExceptionControllerAdvice advice;

	List<CreditCardVO> ccList = new ArrayList<CreditCardVO>();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(advice).build();
		;
		CreditCardVO card = new CreditCardVO("123", "new card", 100.0, 12.0, new Date());
		ccList.add(card);
	}

	@Test
	public void testGetAll() throws Exception {
		Mockito.when(creditCardservice.getAllCards()).thenReturn(ccList);
		mockMvc.perform(get("/ccapp/getall")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testAddCard() throws Exception {

		Mockito.when(creditCardservice.addCard(Mockito.any(CreditCardVO.class)))
				.thenReturn(new CreditCardVO("4586890862091103401", "Gupta", 0.0, 0.0, null));
		mockMvc.perform(
				put("/ccapp/add").contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n" + " \"name\" : \"Gupta\",\r\n"
								+ " \"ccNumber\" : \"4586890862091103401\", \r\n" + " \"creditLimit\": 0.0\r\n" + "}"))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testInvalidAddCard() throws Exception {

		Mockito.when(creditCardservice.getAllCards()).thenReturn(ccList);
		mockMvc.perform(
				put("/ccapp/add").contentType(MediaType.APPLICATION_JSON).content("{\r\n" + " \"name\" : \"Gupta\",\r\n"
						+ " \"ccNumber\" : \"450862091103401\", \r\n" + " \"creditLimit\": 0.0\r\n" + "}"))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testInvalidBalanceAddCard() throws Exception {
		mockMvc.perform(put("/ccapp/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + " \"name\" : \"Gupta\",\r\n" + " \"ccNumber\" : \"4586890862091103401\", \r\n"
						+ " \"creditLimit\": 0.0,\r\n" + " \"creditBalance\": 12.0\r\n" + "}"))
				.andDo(print()).andExpect(status().isBadRequest());

	}

	@Test
	public void testAddDuplicateCardException() throws Exception {
		Mockito.when(creditCardservice.addCard(Mockito.any(CreditCardVO.class)))
				.thenThrow(Mockito.mock(InvalidCardException.class));
		mockMvc.perform(put("/ccapp/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + " \"name\" : \"Gupta\",\r\n" + " \"ccNumber\" : \"4586890862091103401\", \r\n"
						+ " \"creditLimit\": 0.0,\r\n" + " \"creditBalance\": 0.0\r\n" + "}"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

}
