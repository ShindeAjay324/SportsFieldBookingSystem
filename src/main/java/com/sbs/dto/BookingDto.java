package com.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {
	private Integer userId;
	private Integer sportsFieldId;
	private String bookingDate;
	private PaymentDto paymentDto;
	private TimeSlotDto timeSlot;
	
	
}
