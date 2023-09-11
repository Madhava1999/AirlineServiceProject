package com.jsp.airLineProject.entity;


import java.time.LocalTime;

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
public class FlightInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int flightinfotId;
private String flightNumber;
private LocalTime flightTime;
private String flightType;

@OneToOne(cascade = CascadeType.ALL,mappedBy = "flightInfo",fetch = FetchType.EAGER)
//@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//@JoinColumn(name = "flightid")
private Flight flight;
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private AirLine airLine;

}
