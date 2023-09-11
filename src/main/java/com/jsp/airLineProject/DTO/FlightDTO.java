package com.jsp.airLineProject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jsp.airLineProject.entity.Fare;
import com.jsp.airLineProject.entity.Flight;
import com.jsp.airLineProject.entity.FlightInfo;
import com.jsp.airLineProject.entity.Inventory;

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
public class FlightDTO {
	private String desination;
	private LocalDate flightDate;
	private String flightNumber;
	private LocalTime flightTime;
	private String currentLocation;
	private FareDTO fareDTO;
	private InventoryDTO inventoryDTO;
	private FlightInfoDTO flightInfoDTO;
}
