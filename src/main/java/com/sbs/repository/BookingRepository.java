package com.sbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findBybookingDate(String bookingDate);

}
