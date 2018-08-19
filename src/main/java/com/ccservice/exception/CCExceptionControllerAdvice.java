package com.ccservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The controller advice to handle application exceptions
 * @author avpra
 *
 */
@ControllerAdvice
@PropertySource("classpath:message.properties")
public class CCExceptionControllerAdvice {
	@Autowired
    private Environment env;

	/**
	 * The method to handle the invalid card exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(InvalidCardException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody CCErrorResponse handleInvalidCardException(final InvalidCardException exception) {
		CCErrorResponse errorResponse = new CCErrorResponse();
		errorResponse.setCode(exception.getMessage());
		errorResponse.setMessage(env.getProperty(exception.getMessage()));
		return errorResponse;

	}
	
}
