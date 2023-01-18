package com.sbs.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.dto.UserDto;
import com.sbs.entity.User;
import com.sbs.response.Response;
import com.sbs.service.UserService;

@RestController
public class RegisterAndLoginController {
	@Autowired
	private UserService service;
	@Autowired
	private Response response;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserDto userDto){
		
		 UserDto dto= service.register(userDto);
		
		if (dto != null) {
			response.setError(false);
			response.setMessage("User is added");
			response.setStatus("200");
			response.setList(Arrays.asList(dto));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(dto));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody UserDto userDto){
		
		 User user= service.login(userDto);
		
		if (user.getUserId() != null) {
			response.setError(false);
			response.setMessage("Login Successfull");
			response.setStatus("200");
			response.setList(Arrays.asList(user.getUserId()));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(user.getUserId()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	


}
