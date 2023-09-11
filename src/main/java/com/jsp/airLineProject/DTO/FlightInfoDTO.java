package com.jsp.airLineProject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.airLineProject.entity.BookingInformation;
import com.jsp.airLineProject.entity.Passengers;

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
public class FlightInfoDTO {
//	private int flightinfotId;
	private String flightNumber;
	private LocalTime flightTime;
	private String flightType;
	private FlightDTO flightDTO;
	private AirLineDTO airLineDTO;
}
