package com.sbs.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.dto.GetBookingInfoForAdminDto;
import com.sbs.dto.PaymentDto;
import com.sbs.dto.SportsFieldDto;
import com.sbs.dto.UserDto;
import com.sbs.dto.UserDtowithOutPass;
import com.sbs.response.Response;
import com.sbs.service.BookingService;
import com.sbs.service.PaymentService;
import com.sbs.service.SportsFieldService;
import com.sbs.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private SportsFieldService fieldService;
	@Autowired
	private Response response;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/addfields")
	public ResponseEntity<Response> addFields(@RequestBody SportsFieldDto fieldDto){
		log.info("Adding Field"+fieldDto.getName());
		 SportsFieldDto dto= fieldService.addFields(fieldDto);
		
		if (dto != null) {
			response.setError(false);
			response.setMessage("Field is save");
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
	
	@GetMapping("/allUsers")
	public ResponseEntity<Response> getUsers(){
		log.info("Sending All Users");
		List<UserDtowithOutPass> userDtos = userService.getUsers();
		
		if (userDtos != null) {
			response.setError(false);
			response.setMessage("All users are");
			response.setStatus("200");
			response.setList(Arrays.asList(userDtos));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(userDtos));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Response> getUser(@PathVariable Integer id){
		log.info("Getting User of Id"+ id);
		UserDtowithOutPass userDtos = userService.getUser(id);
		if (userDtos != null) {
			response.setError(false);
			response.setMessage("User is shown");
			response.setStatus("200");
			response.setList(Arrays.asList(userDtos));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(userDtos));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer id){
		log.info("Deleting User of Id"+ id);
		Integer Id = userService.deleteUser(id);
		if (Id != null) {
			response.setError(false);
			response.setMessage("User is Deleted");
			response.setStatus("200");
			response.setList(Arrays.asList(Id));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(Id));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Response> changeUserInfo(@RequestBody UserDto userDto,@PathVariable Integer id){
		log.info("Changing User info of"+ id);
		UserDto userDtoReturnInfo = userService.changeUserInfo(userDto,id);
		if (userDtoReturnInfo != null) {
			response.setError(false);
			response.setMessage("Information Updated");
			response.setStatus("200");
			response.setList(Arrays.asList(userDtoReturnInfo));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Information is not updated");
			response.setStatus("401");
			response.setList(Arrays.asList(userDtoReturnInfo));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/payment/{id}")
	public ResponseEntity<Response> updatePaymentInfo(@RequestBody PaymentDto paymentDto,@PathVariable Integer id){
		log.info("Updating payment info of"+ id);
		PaymentDto newPaymentDto = paymentService.updatePaymentInfo(paymentDto,id);
		if (newPaymentDto != null) {
			response.setError(false);
			response.setMessage("Information Updated");
			response.setStatus("200");
			response.setList(Arrays.asList(newPaymentDto));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Information is not updated");
			response.setStatus("401");
			response.setList(Arrays.asList(newPaymentDto));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getBookinginfoAdmin/{id}")
	public ResponseEntity<Response> getBookingInfo(@PathVariable Integer id){
		log.info("Getting booking info of"+ id);
		GetBookingInfoForAdminDto adminDto = bookingService.getBookingInfo(id);
		if (adminDto != null) {
			response.setError(false);
			response.setMessage("Here is your booking information");
			response.setStatus("200");
			response.setList(Arrays.asList(adminDto));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Information is not present");
			response.setStatus("401");
			response.setList(Arrays.asList(adminDto));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
}
