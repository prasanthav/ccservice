package com.ccservice.exception;


/**
 * The exception class used when invalid card is entered
 * @author avpra
 *
 */
public class InvalidCardException extends Exception {
	public InvalidCardException(String message) {
		super(message);
	}

	public InvalidCardException(String message, Throwable cause) {
		super(message, cause);
	}
}
