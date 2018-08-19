package com.ccservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccservice.exception.InvalidCardException;
import com.ccservice.service.CreditCardService;
import com.ccservice.validator.CreditCardValidator;
import com.ccservice.vo.CreditCardVO;

/**
 * The class is the rest controller of the cc application
 * @author avpra
 *
 */
@RestController
@EnableAutoConfiguration
@Validated
@RequestMapping("ccapp")
public class CreditCardController {
	@Autowired
	CreditCardService creditCardService;
	@Autowired
	CreditCardValidator ccValidator;

	/**
	 * The method to get the listof cards from service
	 * @return ResponseEntity<List<CreditCardVO>>
	 * @throws CCApplicatonException
	 */
	@CrossOrigin
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CreditCardVO>> query(){
		List<CreditCardVO> creditCardList = null;
		creditCardList = creditCardService.getAllCards();
		return ResponseEntity.ok().body(creditCardList);
	}

	/**
	 * the method to bind the input validator
	 * @param binder
	 */
	@InitBinder("creditCardVO")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(ccValidator);
	}

	/**
	 * The method to add the credit card to the servide
	 * @param creditCardVO
	 * @return ResponseEntity<Object>
	 * @throws InvalidCardException
	 */
	@CrossOrigin
	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@Valid @RequestBody CreditCardVO creditCardVO)
			throws InvalidCardException{
		return ResponseEntity.ok().body(creditCardService.addCard(creditCardVO));
	}

}
