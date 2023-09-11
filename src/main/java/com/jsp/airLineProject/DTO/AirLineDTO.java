package com.jsp.airLineProject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
public class AirLineDTO {
	//private int airLineId;
	private String airName;
	private List<FlightInfoDTO> flightInfoDTO;
}
