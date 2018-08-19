package com.ccservice.exception;

/**
 * The error response which is returned to the consumer of API
 * @author avpra
 *
 */
public class CCErrorResponse {
	String code;
	String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
