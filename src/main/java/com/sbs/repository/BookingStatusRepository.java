package com.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.BookingStatus;

public interface BookingStatusRepository extends JpaRepository<BookingStatus,Integer> {

}
