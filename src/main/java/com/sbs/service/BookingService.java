package com.sbs.service;

import com.sbs.dto.BookingDto;
import com.sbs.dto.GetBookingInfoForAdminDto;
import com.sbs.dto.GetBookingInfoForUserDto;

public interface BookingService {

	Integer booking(BookingDto bookingDto, Integer userId, Integer sportsFieldId);

	GetBookingInfoForAdminDto getBookingInfo(Integer id);

	GetBookingInfoForUserDto getBookingInfoUser(Integer id);

}
