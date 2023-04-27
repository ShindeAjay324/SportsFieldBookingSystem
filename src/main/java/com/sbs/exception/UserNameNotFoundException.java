package com.sbs.exception;

@SuppressWarnings("serial")
public class UserNameNotFoundException extends RuntimeException {

	public UserNameNotFoundException(String message) {
		super(message);
		
	}
	
}
