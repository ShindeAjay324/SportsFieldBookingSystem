package com.sbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
