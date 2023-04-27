package com.sbs.exception;
@SuppressWarnings("serial")
public class EmailNotFoundException extends RuntimeException {

	public EmailNotFoundException(String message) {
		super(message);
	}

}
