package com.sbs.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sbs.exception.EmailNotFoundException;
import com.sbs.exception.NameNotFoundException;
import com.sbs.exception.PaymentIdNotFoundException;
import com.sbs.exception.UserNotFoundException;
import com.sbs.response.Response;

@RestControllerAdvice
public class AppExceptionController {
	
	@Autowired
	private Response response;
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException ex){
		response.setStatus("404");
		response.setMessage(ex.getMessage());
		//response.setList(null);
		
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Response> emailNotFoundException(EmailNotFoundException ex){
		response.setStatus("404");
		response.setMessage(ex.getMessage());
		response.setList(null);
		
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<Response> nameNotFoundException(NameNotFoundException ex){
		response.setStatus("404");
		response.setMessage(ex.getMessage());
		response.setList(null);
		
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentIdNotFoundException.class)
	public ResponseEntity<Response> paymentIdNotFoundException(PaymentIdNotFoundException ex){
		response.setStatus("404");
		response.setMessage(ex.getMessage());
		response.setList(null);
		
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
	}

}
