package com.sbs.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.dto.BookingDto;
import com.sbs.dto.GetBookingInfoForUserDto;
import com.sbs.entity.SportsField;
import com.sbs.response.Response;
import com.sbs.service.BookingService;
import com.sbs.service.SportsFieldService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	@Autowired
	private Response response;
	@Autowired
	private SportsFieldService sportsFieldService;
	@Autowired
	private BookingService bookingService;

	@GetMapping("/showSportsFields")
	public ResponseEntity<Response> showSportsFields() {
		log.info("Showing sports field");
		List<SportsField> sportsField = sportsFieldService.showSportsFields();
		if (sportsField != null) {
			response.setError(false);
			response.setMessage("These are all the Fields");
			response.setStatus("200");
			response.setList(Arrays.asList(sportsField));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Oops something is wrong");
			response.setStatus("401");
			response.setList(Arrays.asList(sportsField));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/user/{userId}/sportsField/{sportsFieldId}/booking")
	public ResponseEntity<Response> booking(@RequestBody BookingDto bookingDto, @PathVariable Integer userId,
			@PathVariable Integer sportsFieldId) {
		log.info("Adding booking of user "+userId+" of sports field "+sportsFieldId);
		Integer bookingId = bookingService.booking(bookingDto,userId,sportsFieldId);
		if (bookingId != null) {
			response.setError(false);
			response.setMessage("Booking Successfull");
			response.setStatus("200");
			response.setList(Arrays.asList(bookingId));
			return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			response.setError(true);
			response.setMessage("Field or date is not Available");
			response.setStatus("401");
			response.setList(Arrays.asList(bookingId));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getBookinginfoUser/{id}")
	public ResponseEntity<Response> getBookingInfo(@PathVariable Integer id){
		log.info("Retriving Booking info of Id"+ id);
		GetBookingInfoForUserDto adminDto = bookingService.getBookingInfoUser(id);
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
