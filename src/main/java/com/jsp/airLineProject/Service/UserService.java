package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.util.List;

import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.PassergersDTO;
import com.jsp.airLineProject.DTO.UserDTO;
import com.jsp.airLineProject.entity.Flight;

public interface UserService {
	boolean userRegistration(UserDTO userDTO);
	UserDTO userIogIn(String userid,String password);
	int bookingflightTicket(List<PassergersDTO> passergersDTO,String from,String to);
	boolean bookingflightTicketBynumber(List<PassergersDTO> passergersDTO,String number);

}
