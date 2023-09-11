package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FareDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.DTO.InventoryDTO;
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

import lombok.ToString;
@Service

public class FlightServiceImpl implements FlightService{
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
	public List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName(String from, String to,
			LocalDate date, String flightname) {
		// TODO Auto-generated method stub
		if (airLineRepository.findAirLineByName(flightname)!=null) {
		//	System.out.println( flightRepository.findByFromTOAndDate(from, to, date));
			System.out.println("==============================================1");
			List<Flight> findByFromTOAndDate = flightRepository.findByFromTOAndDate(from, to, date);
			System.out.println("==============================================2");
//			System.out.println(findByFromTOAndDate);
			System.out.println("==============================================3");
			
			if (findByFromTOAndDate!=null) {
					List<FlightDTO> filghtDTO=findByFromTOAndDate.stream().map(f->FlightDTO.builder()
							.desination(f.getDesination())
							.flightDate(f.getFlightDate())
							.flightNumber(f.getFlightNumber())
							.flightTime(f.getFlightTime())
							.currentLocation(f.getCurrentLocation())
							.fareDTO(FareDTO.builder()
									.amount(f.getFare().getAmount())
									.currency(f.getFare().getCurrency())
									.build())
							.inventoryDTO(InventoryDTO.builder()
									.count(f.getInventory().getCount())
									.build())
							.flightInfoDTO(FlightInfoDTO.builder()
									.flightTime(f.getFlightInfo().getFlightTime())
									.flightType(f.getFlightInfo().getFlightType())
									.airLineDTO(AirLineDTO.builder()
											.airName(f.getFlightInfo().getAirLine().getAirName())
											.build())
									.build())
							.build()).toList();
					return filghtDTO;
			} else {
				return null;
			}
		} else {
					return null;
		}
		
	
	}
	public FlightDTO findflightbyStratingAndEndingLocationsDateAndFlightNumber(String from, String to,
			LocalDate date, String flightnumber) {
		// TODO Auto-generated method stub
	
			
			System.out.println("==============================================1");
			Flight f = flightRepository.findByFromTOAndDateMain(from, to, date,flightnumber);
			System.out.println("==============================================2");
  
			System.out.println("==============================================3");
			
			if (f!=null) {
					FlightDTO filghtDTO=FlightDTO.builder()
							.desination(f.getDesination())
							.flightDate(f.getFlightDate())
							.flightNumber(f.getFlightNumber())
							.flightTime(f.getFlightTime())
							.currentLocation(f.getCurrentLocation())
							.fareDTO(FareDTO.builder()
									.amount(f.getFare().getAmount())
									.currency(f.getFare().getCurrency())
									.build())
							.inventoryDTO(InventoryDTO.builder()
									.count(f.getInventory().getCount())
									.build())
							.flightInfoDTO(FlightInfoDTO.builder()
									.flightTime(f.getFlightInfo().getFlightTime())
									.flightType(f.getFlightInfo().getFlightType())
									.airLineDTO(AirLineDTO.builder()
											.airName(f.getFlightInfo().getAirLine().getAirName())
											.build())
									.build())
							.build();
					return filghtDTO;
			} else {
				return null;
			}
		} 
		
	
	
	@Override
	public FlightDTO searchFlightByUsingAirLinenameFlightnumberDateTiming(String name, String number, LocalDate date,
			LocalTime time) {
		// TODO Auto-generated method stub
		System.out.println("name : "+name+" number : "+number+"  date : " +date +"  time : "+time);
		System.out.println("==================================== 1 =======================");
		if (airLineRepository.findAirLineByName(name)!=null) {
			System.out.println("==================================== 2 =======================");
		FlightInfo findByNumberFlightnumberTimeDate = flightinfoRepository.findByNumberFlightnumberTimeDate(number, time, date);
		System.out.println("==================================== 3 =======================");
	//	Flight flight = findByNumberFlightnumberTimeDate.getFlight();
		System.out.println("==================================== 4 =======================");
										if (findByNumberFlightnumberTimeDate!=null) {
											System.out.println("==================================== 5 =======================");
																		FlightDTO build = FlightDTO.builder()
																		.currentLocation(findByNumberFlightnumberTimeDate.getFlight().getCurrentLocation())
																		.desination(findByNumberFlightnumberTimeDate.getFlight().getDesination())
																		.flightNumber(findByNumberFlightnumberTimeDate.getFlight().getFlightNumber())
																		.flightDate(findByNumberFlightnumberTimeDate.getFlight().getFlightDate())
																		.flightTime(findByNumberFlightnumberTimeDate.getFlight().getFlightTime())
																		.fareDTO(FareDTO.builder()
																				.amount(findByNumberFlightnumberTimeDate.getFlight().getFare().getAmount())
																				.currency(findByNumberFlightnumberTimeDate.getFlight().getFare().getCurrency())
																				.build())
																		.inventoryDTO(InventoryDTO.builder()
																				.count(findByNumberFlightnumberTimeDate.getFlight().getInventory().getCount())
																				.build())
																		.build();
											return build;
										} else {
											return null;
										}
		} else {
																												return null;
																														}
	}
	@Override
	public List<FlightDTO> searchFlightByUsingFlightnumberDateTimingShowSalaryInAscendingOrder(String number,
			LocalDate date, LocalTime time) {
		// TODO Auto-generated method stub
		System.out.println(" number : "+number+"  date : " +date +"  time : "+time);
		System.out.println("=====================  1  ==============================");
		List<FlightInfo> findbyNumberFlightnumberTimeDate = flightinfoRepository.findbyNumberFlightnumberTimeDate(number, time, date);
		System.out.println("=====================  2  ==============================");
		int size = findbyNumberFlightnumberTimeDate.size();
		System.out.println("size : "+size);
		if (findbyNumberFlightnumberTimeDate!=null) {
			System.out.println("=====================  3  ==============================");
			List<FlightDTO> list = findbyNumberFlightnumberTimeDate.stream().map(f->FlightDTO.builder()
					.currentLocation(f.getFlight().getCurrentLocation())
					.desination(f.getFlight().getDesination())
					.flightNumber(f.getFlight().getFlightNumber())
					.flightTime(f.getFlight().getFlightTime())
					.flightDate(f.getFlight().getFlightDate())
					.fareDTO(FareDTO.builder()
							.currency(f.getFlight().getFare().getCurrency())
							.amount(f.getFlight().getFare().getAmount())
							.build())
					.inventoryDTO(InventoryDTO.builder()
							.count(f.getFlight().getInventory().getCount())
							.build())
					.build()).toList();
			System.out.println("=====================  4  ==============================");
			List<FlightDTO> list2 = list.stream().sorted(Comparator.comparingDouble(flightdto -> flightdto.getFareDTO().getAmount())).toList();
	
//			int amountzero=0;
//			for (FlightDTO flightDTO : list) {
//				double amount = flightDTO.getFareDTO().getAmount();
//				if (amount>amountzero) {
//					amountzero=(int) amount;
//					
//				}
		//	}
			System.out.println("=====================  5  ==============================");
		return list2;
		} else {
				return null;
	       	}
	
	}
	@Override
	public AirLineDTO search(String name, String number, LocalDate date, LocalTime time) {
		// TODO Auto-generated method stub
		AirLine findByAirlinenameFlightnumberFlightdateFlighttimings = airLineRepository.findByAirlinenameFlightnumberFlightdateFlighttimings(name, number, time, date);
		if (findByAirlinenameFlightnumberFlightdateFlighttimings!=null) {
			
		
		List<FlightInfo> flightInfo = findByAirlinenameFlightnumberFlightdateFlighttimings.getFlightInfo();
		List<FlightInfoDTO> list = flightInfo.stream().map(f->FlightInfoDTO.builder()
				.flightNumber(f.getFlightNumber())
				.flightTime(f.getFlightTime())
				.flightType(f.getFlightType())
				.flightDTO(FlightDTO.builder()
						.desination(f.getFlight().getDesination())
						.flightDate(f.getFlight().getFlightDate())
						.flightNumber(f.getFlight().getFlightNumber())
						.flightTime(f.getFlight().getFlightTime())
						.currentLocation(f.getFlight().getCurrentLocation())
						.build())
				.build()).toList();
			AirLineDTO build = AirLineDTO.builder()
			.airName(findByAirlinenameFlightnumberFlightdateFlighttimings.getAirName())
			.flightInfoDTO(list)
			.build();
		return build;
		}
		else
		{
			return null;
		}
	}
	
	//===========================================================================  in class ===========================================
	public List<FlightDTO> getAllflight(){
//		flightRepository.findAll().stream().map(f-> FlightDTO.builder()
//				.currentLocation(f.)
//				.build()).toList();
		return null;
	}
		
	public void findthesumofvalues() {
		int arr[]= {2,11,7,1,0};
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
			//	int i = arr[j];
				
			}
			
		}
	}
	@Override
	public List<FlightDTO> findflightbyStratingAndEndingLocationsDate(String from, String to, LocalDate date) {
		// TODO Auto-generated method stub
		List<Flight> findByFromTOAndDate = flightRepository.findByFromTOAndDate(from, to, date);
		System.out.println("==============================================2");

		System.out.println("==============================================3");
		
		if (findByFromTOAndDate!=null) {
				List<FlightDTO> filghtDTO=findByFromTOAndDate.stream().map(f->FlightDTO.builder()
						.desination(f.getDesination())
						.flightDate(f.getFlightDate())
						.flightNumber(f.getFlightNumber())
						.flightTime(f.getFlightTime())
						.currentLocation(f.getCurrentLocation())
						.fareDTO(FareDTO.builder()
								.amount(f.getFare().getAmount())
								.currency(f.getFare().getCurrency())
								.build())
						.inventoryDTO(InventoryDTO.builder()
								.count(f.getInventory().getCount())
								.build())
						.flightInfoDTO(FlightInfoDTO.builder()
								.flightTime(f.getFlightInfo().getFlightTime())
								.flightType(f.getFlightInfo().getFlightType())
								.airLineDTO(AirLineDTO.builder()
										.airName(f.getFlightInfo().getAirLine().getAirName())
										.build())
								.build())
						.build()).toList();
				return filghtDTO;
		} else {
			return null;
		}
	}
}
