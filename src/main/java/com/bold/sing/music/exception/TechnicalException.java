package com.bold.sing.music.exception;

/**
 * Exception type for all technical (unexpected) errors that cannot be handled from the application.
 */
public class TechnicalException extends RuntimeException {

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}
