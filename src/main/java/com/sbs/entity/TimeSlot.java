package com.sbs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer timeSlotId;
	@Column(name = "start_time",length=50)
	private String startTime;
	@Column(name = "end_time",length=50)
	private String endTime;
	
	@OneToMany(mappedBy = "timeSlot")
	private List<Booking> bookings;
	

}
