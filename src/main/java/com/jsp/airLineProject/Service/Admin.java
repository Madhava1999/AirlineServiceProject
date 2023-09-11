package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.util.List;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.BookingInformationDTO;
import com.jsp.airLineProject.DTO.FareDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.DTO.InventoryDTO;
import com.jsp.airLineProject.DTO.PassergersDTO;

public interface Admin {
int addFlightDetails(AirLineDTO airLineDTO);
int bookingFlight(BookingInformationDTO bookingInformationDTO);
BookingInformationDTO findBookingDetails(int id);
List<BookingInformationDTO> getAllBookingDetails();
FlightDTO findByFlightId(int id);
int addnewFlights(int id,FlightInfoDTO flightInfoDTO);
//==================================
boolean adminlogin(String id,String password);
int addairline(AirLineDTO airLineDTO);
int addFlightinfoWithAirlineId(int id,FlightInfoDTO  flightInfoDTO);
int addFlightwithFlightinfo(FlightDTO flightDTO,int fareid,int inventoryid);
int addInventory(InventoryDTO inventoryDTO);
int addFare(FareDTO fareDTO);
//////////////////////
int bookingflightTicket(List<PassergersDTO> passergersDTO,String from,String to);
int deleteAirLineById(int id);
int deleteFlightById(int id);
}
