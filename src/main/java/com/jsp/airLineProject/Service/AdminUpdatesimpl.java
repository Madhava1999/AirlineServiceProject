package com.jsp.airLineProject.Service;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.Repository.AirLineRepository;
import com.jsp.airLineProject.Repository.BookingInformationRepository;
import com.jsp.airLineProject.Repository.CheckInRepository;
import com.jsp.airLineProject.Repository.FlightRepository;
import com.jsp.airLineProject.Repository.FlightinfoRepository;
import com.jsp.airLineProject.Repository.PassergersRepository;
import com.jsp.airLineProject.Repository.UserRepository;
import com.jsp.airLineProject.entity.AirLine;
import com.jsp.airLineProject.entity.Flight;
import com.jsp.airLineProject.entity.FlightInfo;
@Service
public class AdminUpdatesimpl  implements AdminUpdates{
	@Autowired
	private BookingInformationRepository bookingInformationRepository;
	@Autowired
	private PassergersRepository passergersRepository;
	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AirLineRepository airLineRepository;
	@Autowired
	private CheckInRepository checkInRepository;
	@Autowired
	private FlightinfoRepository flightinfoRepository;
	@Override
	public int updateAirLine(String name, int id) {
		// TODO Auto-generated method stub
		System.out.println("name : "+name+"  id  : "+id);
		Optional<AirLine> findById = airLineRepository.findById(id);
		if (findById.isPresent()) {
			AirLine airLine = findById.get();
			airLine.setAirName(name);
			airLineRepository.save(airLine);
			return airLineRepository.save(airLine).getAirLineId();
		}
		return 0;
	}
	@Override
	public int updateFlightinfoDetails(int id, FlightInfoDTO flightInfoDTO,int airlineId) {
		// TODO Auto-generated method stub
		Optional<FlightInfo> findById = flightinfoRepository.findById(id);
		if (findById.isPresent()) {
			FlightInfo flightInfo = findById.get();
			int flightId = flightInfo.getFlight().getFlightId();
			System.out.println("flight id : "+flightId);
			String flightNumber = flightInfoDTO.getFlightNumber();
			LocalTime flightTime = flightInfoDTO.getFlightTime();
			if (flightNumber!=null  && flightTime!=null) {
				System.out.println("======================== 1 =================");
				flightInfo.setFlightNumber(flightInfoDTO.getFlightNumber());
				flightInfo.setFlightTime(flightInfoDTO.getFlightTime());
				flightInfo.setFlightType(flightInfoDTO.getFlightType());
				System.out.println("======================== 2 =================");
					Flight flight = flightRepository.findById(flightId).get();
					flight.setFlightNumber(flightInfoDTO.getFlightNumber());
					flight.setFlightTime(flightInfoDTO.getFlightTime());
					flight.setFlightInfo(flightInfo);
					flightRepository.save(flight);
				System.out.println("======================== 3 =================");
			}
			else {
				System.out.println("======================== 4 =================");
				flightInfo.setFlightNumber(flightInfo.getFlightNumber());
				flightInfo.setFlightTime(flightInfo.getFlightTime());
				flightInfo.setFlightType(flightInfo.getFlightType());
				System.out.println("======================== 5 =================");
			}
			flightInfo.setAirLine(AirLine.builder()
				.airLineId(airlineId)
				.build());
						
			return flightinfoRepository.save(flightInfo).getFlightinfotId();
		}
		return 0;
	}

}
