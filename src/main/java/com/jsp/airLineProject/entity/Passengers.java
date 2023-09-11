package com.jsp.airLineProject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Passengers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int passengerId;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String mobileNumber;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private BookingInformation bookingInformation;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="check_Id")
	private Check_In check_In;
	
}
