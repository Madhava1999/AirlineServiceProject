package com.jsp.airLineProject.Service;

import com.jsp.airLineProject.DTO.FlightInfoDTO;

public interface AdminUpdates {
int updateAirLine(String name,int id);
int updateFlightinfoDetails(int id,FlightInfoDTO flightInfoDTO,int airlineID);
}
