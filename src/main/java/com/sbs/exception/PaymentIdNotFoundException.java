package com.sbs.exception;
@SuppressWarnings("serial")
public class PaymentIdNotFoundException extends RuntimeException {

	public PaymentIdNotFoundException(String message) {
		super(message);
	}
	
}
