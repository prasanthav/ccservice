package com.ccservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The credit card spring application
 * 
 * @author avpra
 *
 */
@SpringBootApplication
public class CreditCardApplication {
	private static Logger logger = LoggerFactory.getLogger(CreditCardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplication.class, args);
		logger.info("Credit Card Application Started");
	}
}
