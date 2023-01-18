package com.sbs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class SportsField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sportsFieldId;
	@Column(name = "sport_field",length=50,nullable = false)
	private String name;
	@Column(name = "location",length=50,nullable = false)
	private String location;
	@Column(name = "field_capacity",length=50,nullable = false)
	private Integer capacity;
	@OneToMany(mappedBy = "sportsField")
	@JsonBackReference
	private List<Booking> bookings;
}
