package com.jsp.airLineProject.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity

public class BookingInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookintId;
	private LocalDate bookingDate;
	private String desitinaion;
	private double fare;  //cost
	private LocalDate flightDate;
	private String flightNumber;
	private boolean status;
	private LocalTime flightTime;
	private String currentCity;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "bookingInformation")
	private List<Passengers> passengers;
}
