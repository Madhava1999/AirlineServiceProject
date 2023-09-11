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
import javax.persistence.JoinColumn;
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
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int flightId;
private String desination;
private LocalDate flightDate;
private String flightNumber;
private LocalTime flightTime;
private String currentLocation;


@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "fareId")
private Fare fare;

@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinColumn(name="inventoryid")
private Inventory inventory;
@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinColumn(name = "flightinfotId")
//@OneToOne(cascade = CascadeType.ALL,mappedBy = "flight",fetch = FetchType.EAGER)
private FlightInfo flightInfo;





}
