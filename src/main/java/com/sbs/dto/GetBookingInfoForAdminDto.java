package com.sbs.dto;

import com.sbs.dto.get_all_info_for_admin.PaymentDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.SportsFieldDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.TimeSlotDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.UserDtoAdmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetBookingInfoForAdminDto {
	private Integer bookingId;
	private String bookingDate;
	private UserDtoAdmin user;
	private SportsFieldDtoAdmin sportsField;
	private PaymentDtoAdmin paymentDto;
	private TimeSlotDtoAdmin timeSlot;
}
