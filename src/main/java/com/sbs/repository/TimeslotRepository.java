package com.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.TimeSlot;

public interface TimeslotRepository extends JpaRepository<TimeSlot, Integer> {

}
