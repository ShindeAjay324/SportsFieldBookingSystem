package com.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetBookingInfoForUserDto {
	private Integer bookingId;
	private String bookingDate;
	private UserDtowithOutPass user;
	private SportsFieldDto sportsField;
	private PaymentDto paymentDto;
	private TimeSlotDto timeSlot;
}
