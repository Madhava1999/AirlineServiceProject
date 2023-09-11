package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FlightDTO;

public interface FlightService {
	 List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName(String from, String to,
			LocalDate date, String flightname) ;
	FlightDTO findflightbyStratingAndEndingLocationsDateAndFlightNumber(String from,String to,LocalDate date,String flightnumber);
	FlightDTO searchFlightByUsingAirLinenameFlightnumberDateTiming(String name,String number,LocalDate date,LocalTime time);
	List<FlightDTO> searchFlightByUsingFlightnumberDateTimingShowSalaryInAscendingOrder(String number,LocalDate date,LocalTime time);
	AirLineDTO search(String name,String number,LocalDate date,LocalTime time);
	List<FlightDTO> findflightbyStratingAndEndingLocationsDate(String from,String to,LocalDate date);

}
