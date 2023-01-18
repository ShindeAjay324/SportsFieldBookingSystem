package com.sbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(name = "booking_date")
	private String bookingDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "sports_field_id")
	private SportsField sportsField;
	
	@ManyToOne
	@JoinColumn(name = "times_slot_id")
	private TimeSlot timeSlot;
	
	@ManyToOne
	@JoinColumn(name = "booking_status_id")
	private BookingStatus status;
	
	@OneToOne
	private Payment payment;

}
