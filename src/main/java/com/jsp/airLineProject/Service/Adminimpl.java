package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.BookingInformationDTO;
import com.jsp.airLineProject.DTO.CheckInDTO;
import com.jsp.airLineProject.DTO.FareDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.DTO.InventoryDTO;
import com.jsp.airLineProject.DTO.PassergersDTO;
import com.jsp.airLineProject.Repository.AdminloginRepository;
import com.jsp.airLineProject.Repository.AirLineRepository;
import com.jsp.airLineProject.Repository.BookingInformationRepository;
import com.jsp.airLineProject.Repository.CheckInRepository;
import com.jsp.airLineProject.Repository.FareRepository;
import com.jsp.airLineProject.Repository.FlightRepository;
import com.jsp.airLineProject.Repository.FlightinfoRepository;
import com.jsp.airLineProject.Repository.InventoryRepository;
import com.jsp.airLineProject.Repository.PassergersRepository;
import com.jsp.airLineProject.entity.Adminlogin;
import com.jsp.airLineProject.entity.AirLine;
import com.jsp.airLineProject.entity.BookingInformation;
import com.jsp.airLineProject.entity.Check_In;
import com.jsp.airLineProject.entity.Fare;
import com.jsp.airLineProject.entity.Flight;
import com.jsp.airLineProject.entity.FlightInfo;
import com.jsp.airLineProject.entity.Inventory;
import com.jsp.airLineProject.entity.Passengers;
@Service
public class Adminimpl implements Admin {
	@Autowired
	private AirLineRepository airLineRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private FareRepository fareRepository;
	@Autowired
	private BookingInformationRepository bookingInformationRepository;
	@Override
	public int addFlightDetails(AirLineDTO airLineDTO) {
		List<FlightInfoDTO> flightInfoDTO = airLineDTO.getFlightInfoDTO();
		
		System.out.println(flightInfoDTO+"======================================1");
		List<FlightInfo> collect = flightInfoDTO.stream().map(info->   
			
                                 FlightInfo.builder()
		    		   .flightNumber(info.getFlightNumber())
		    		   .flightTime(info.getFlightTime())
		    		   .flightType(info.getFlightType())
		    		   .flight(Flight.builder()
		    				 
		    				   .fare(Fare.builder()
		    						   .currency(info.getFlightDTO().getFareDTO().getCurrency())
		    						   .amount(info.getFlightDTO().getFareDTO().getAmount())
		    						   .build())
		    				   .inventory(Inventory.builder()
		    						   .count(info.getFlightDTO().getInventoryDTO().getCount())
		    						   .build())
		    				  
		    						  
		    				   .desination(info.getFlightDTO().getDesination())
		    				   .flightDate(info.getFlightDTO().getFlightDate())
		    				   .flightNumber(info.getFlightDTO().getFlightNumber())
		    				   .flightTime(info.getFlightDTO().getFlightTime())
		    				   .currentLocation(info.getFlightDTO().getCurrentLocation())
		    				  
		    				   
		    				   .build())
		    		        
		    		 
		    		   .build()
		        		
		) .collect(Collectors.toList());

		AirLine airLine=AirLine.builder()
				.airName(airLineDTO.getAirName())
				.flightInfo(collect).build();
				
			collect.forEach(f->f.setAirLine(airLine));
	
			
			
			return airLineRepository.save(airLine).getAirLineId();
	
	}

	@Override
	public int bookingFlight(BookingInformationDTO bookingInformationDTO) {
	
		List<Passengers> passengersList = bookingInformationDTO.getPassergersDTO().stream().map(f->Passengers.builder()
				.firstName(f.getFirstName())
				.lastName(f.getLastName())
				.email(f.getEmail())
				.gender(f.getGender())
				.mobileNumber(f.getMobileNumber())
				.check_In(Check_In.builder()
						.gateNumber(f.getCheckInDTO().getGateNumber())
						.seatNumber(f.getCheckInDTO().getSeatNumber())
						.build())
				.build()).toList();
		BookingInformation bookingInformation=BookingInformation.builder()
				.bookingDate(bookingInformationDTO.getBookingDate())
				.desitinaion(bookingInformationDTO.getDesitinaion())
				.fare(bookingInformationDTO.getFare())
				.flightDate(bookingInformationDTO.getFlightDate())
				.flightNumber(bookingInformationDTO.getFlightNumber())
				.status(bookingInformationDTO.isStatus())
				.flightTime(bookingInformationDTO.getFlightTime())
				.currentCity(bookingInformationDTO.getCurrentCity())
				.passengers(passengersList)
				.build();
		passengersList.forEach(p->p.setBookingInformation(bookingInformation));
		
		return bookingInformationRepository.save(bookingInformation).getBookintId();
	}
	@Override
	public BookingInformationDTO findBookingDetails(int id) {
		BookingInformation  bookinfinfo = bookingInformationRepository.findByBookingDetailsId(id);
	
		List<PassergersDTO>  passergersDTOList= bookinfinfo.getPassengers().stream().map(p->PassergersDTO.builder()
				.firstName(p.getFirstName())
				.lastName(p.getLastName())
				.email(p.getEmail())
				.gender(p.getGender())
				.mobileNumber(p.getMobileNumber())
				.checkInDTO(CheckInDTO.builder()
						.gateNumber(p.getCheck_In().getGateNumber())
						.seatNumber(p.getCheck_In().getSeatNumber())
						.build())
				.build()).collect(Collectors.toList());
		
		
		
		BookingInformationDTO bookingInformationDTO=BookingInformationDTO.builder()
				.bookingDate(bookinfinfo.getBookingDate())
				.desitinaion(bookinfinfo.getDesitinaion())
				.fare(bookinfinfo.getFare())
				.flightDate(bookinfinfo.getFlightDate())
				.flightNumber(bookinfinfo.getFlightNumber())
				.status(bookinfinfo.isStatus())
				.flightTime(bookinfinfo.getFlightTime())
				.currentCity(bookinfinfo.getCurrentCity())
				.passergersDTO(passergersDTOList)
				.build();
		return bookingInformationDTO;
	}
	@Override
	public List<BookingInformationDTO> getAllBookingDetails() {
		List<BookingInformation> bookinfinfo = bookingInformationRepository.findAll();
		List<BookingInformationDTO> list = bookinfinfo.stream().map(b->BookingInformationDTO.builder()
				.bookingDate(b.getBookingDate())
				.desitinaion(b.getDesitinaion())
				.fare(b.getFare())
				.flightDate(b.getFlightDate())
				.flightNumber(b.getFlightNumber())
				.status(b.isStatus())
				.flightTime(b.getFlightTime())
				.currentCity(b.getCurrentCity())
				.passergersDTO(b.getPassengers().stream().map(p->PassergersDTO.builder()
						.firstName(p.getFirstName())
						.lastName(p.getLastName())
						.email(p.getEmail())
						.gender(p.getGender())
						.mobileNumber(p.getMobileNumber())
						.checkInDTO(CheckInDTO.builder()
								.gateNumber(p.getCheck_In().getGateNumber())
								.seatNumber(p.getCheck_In().getSeatNumber())
								.build())
						.build()).toList())
				.build()).toList();
		return list;
	}
	@Autowired
	private FlightRepository flightRepository;
	@Override
	public FlightDTO findByFlightId(int id) {
		 Flight findByFlightDetailsId = flightRepository.findByFlightDetailsId(id);
		 							
		                  FlightInfo flightInfo = findByFlightDetailsId.getFlightInfo();
		                  
		 	FlightDTO flightdto=FlightDTO.builder()
		 			.desination(findByFlightDetailsId.getDesination())
		 			.flightDate(findByFlightDetailsId.getFlightDate())
		 			.flightNumber(findByFlightDetailsId.getFlightNumber())
		 			.flightTime(findByFlightDetailsId.getFlightTime())
		 			.currentLocation(findByFlightDetailsId.getCurrentLocation())
		 			.fareDTO(FareDTO.builder()
		 					.currency(findByFlightDetailsId.getFare().getCurrency())
		 					.amount(findByFlightDetailsId.getFare().getAmount())
		 					.build())
		 			.inventoryDTO(InventoryDTO.builder()
		 					.count(findByFlightDetailsId.getInventory().getCount())
		 					.build())
		 			.flightInfoDTO(FlightInfoDTO.builder()
		 					.flightNumber(findByFlightDetailsId.getFlightInfo().getFlightNumber())
		 					.flightTime(findByFlightDetailsId.getFlightInfo().getFlightTime())
		 					.flightType(findByFlightDetailsId.getFlightInfo().getFlightType())
		 					.airLineDTO(AirLineDTO.builder()
		 							.airName(flightInfo.getAirLine().getAirName())
		 							.build())
		 					.build())
		 			.build();
		return flightdto;
	}
	@Autowired
	private FlightinfoRepository flightinfoRepository;
	@Override
	public int addnewFlights(int id, FlightInfoDTO flightInfoDTO) {
		       AirLine findAirLineById = airLineRepository.findAirLineById(id);
		       
		       FlightInfo flightInfo=FlightInfo.builder()
		    		   .flightNumber(flightInfoDTO.getFlightNumber())
		    		   .flightTime(flightInfoDTO.getFlightTime())
		    		   .flightType(flightInfoDTO.getFlightType())
		    		   .flight(Flight.builder()
		    				   .desination(flightInfoDTO.getFlightDTO().getDesination())
		   		 			.flightDate(flightInfoDTO.getFlightDTO().getFlightDate())
		   		 			.flightNumber(flightInfoDTO.getFlightDTO().getFlightNumber())
		   		 			.flightTime(flightInfoDTO.getFlightDTO().getFlightTime())
		   		 			.currentLocation(flightInfoDTO.getFlightDTO().getCurrentLocation())
		   		 			.fare(Fare.builder()
		   		 					.currency(flightInfoDTO.getFlightDTO().getFareDTO().getCurrency())
		   		 					.amount(flightInfoDTO.getFlightDTO().getFareDTO().getAmount())
		   		 					.build())
		   		 			.inventory(Inventory.builder()
		   		 					.count(flightInfoDTO.getFlightDTO().getInventoryDTO().getCount())
		   		 					.build())
		    				  .build())
		    		   .airLine(findAirLineById)
		    		     .build();
		       return flightinfoRepository.save(flightInfo).getFlightinfotId();				
	}
	
	// =======================================================
	//    Add airline   
	@Override
	public int addairline(AirLineDTO airLineDTO) {
	
		     AirLine findAirLineByName = airLineRepository.findAirLineByName(airLineDTO.getAirName()) ;
		     if (findAirLineByName!=null) {
				return 0;
			}
		     else
		     {
		    	 return airLineRepository.save(AirLine.builder().airName(airLineDTO.getAirName()).build()).getAirLineId();
		     }
	}
	@Override
	public int addFlightinfoWithAirlineId(int id,FlightInfoDTO  flightInfoDTO) {
	    AirLine findAirLineById = airLineRepository.findById(id).get();
	    System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
	    FlightInfo checkFlightinfoNumber = flightinfoRepository.checkFlightinfoNumber(flightInfoDTO.getFlightNumber());
	    System.out.println("====================================================================");
	    									if (checkFlightinfoNumber !=null) {
	    										return 0;
											} else {
//												System.out.println(FlightInfo.builder()
//	    										    		.flightNumber(flightInfoDTO.getFlightNumber())
//	    										    		.flightTime(flightInfoDTO.getFlightTime())
//	    										    		.flightType(flightInfoDTO.getFlightType())
//	    										    		.airLine(findAirLineById)
//	    										    		.build());
												 return  flightinfoRepository.save(FlightInfo.builder()
	    										    		.flightNumber(flightInfoDTO.getFlightNumber())
	    										    		.flightTime(flightInfoDTO.getFlightTime())
	    										    		.flightType(flightInfoDTO.getFlightType())
	    										    		.airLine(findAirLineById)
	    										    		.build())  .getFlightinfotId();
											}
	    									
	   
	}
	@Override
	public int addFlightwithFlightinfo(FlightDTO flightDTO,int fareid,int inventoryid1) {
		FlightInfo checkFlightinfoNumber = flightinfoRepository.checkFlightinfoNumber(flightDTO.getFlightNumber());
		Fare fare = fareRepository.findById(fareid).get();
	//	int id=inventoryid1;
	Inventory inventory	=inventoryRepository.findById(inventoryid1).get();
		if (checkFlightinfoNumber!=null) {
				return  flightRepository.save(Flight.builder()
					   .desination(flightDTO.getDesination())
					   .flightDate(flightDTO.getFlightDate())
					   .flightNumber(flightDTO.getFlightNumber())
					   .flightTime(checkFlightinfoNumber.getFlightTime())
					   .currentLocation(flightDTO.getCurrentLocation())
					 .flightInfo(checkFlightinfoNumber)
					 .fare(fare)
					 .inventory(inventory)
					  .build()) .getFlightId();
		} else {
			return 0;
		}
		
	}
	
	@Override
	public int addInventory(InventoryDTO inventoryDTO) {
		return inventoryRepository.save(Inventory.builder()
				.count(inventoryDTO.getCount())
				.build()).getInventoryid();
	}
	
	@Override
	public int addFare(FareDTO fareDTO) {
		// TODO Auto-generated method stub
		return fareRepository.save(Fare.builder()
				.amount(fareDTO.getAmount())
				.currency(fareDTO.getCurrency())
				.build()).getFareId();
	}
	//=====================================  For Booking  ===================================================
	@Autowired
	private PassergersRepository passergersRepository;
	@Override
	public int bookingflightTicket(List<PassergersDTO>  passergersDTO, String from, String to) {
		System.out.println(passergersDTO+"++============================================");
				Flight findByFromTO = flightRepository.findByFromTO(from, to);
				if (findByFromTO!=null) {
					BookingInformation build = BookingInformation.builder()
					.bookingDate(LocalDate.now())//======================//
					.desitinaion(findByFromTO.getDesination())
					.currentCity(findByFromTO.getCurrentLocation())
					.fare(findByFromTO.getFare().getAmount())
					.flightTime(findByFromTO.getFlightTime())
					.flightNumber(findByFromTO.getFlightNumber())
					.status(true)
					.flightDate(findByFromTO.getFlightDate())
					.build();
					BookingInformation save = bookingInformationRepository.save(build);
					BookingInformation findById = bookingInformationRepository.findById(save.getBookintId()).get();
					List<Passengers> list = passergersDTO.stream().map(p->Passengers.builder()
							.firstName(p.getFirstName())
							.lastName(p.getLastName())
							.mobileNumber(p.getMobileNumber())
							.gender(p.getGender())
							.email(p.getEmail())
							.bookingInformation(findById)
							.check_In(Check_In.builder()
									.gateNumber(gatenumber())
									.seatNumber(seatnumber())
									.build())
							.build()).toList();
					/*
					 * Passengers build2 = Passengers.builder() .email(passergersDTO.getEmail())
					 * .firstName(passergersDTO.getFirstName())
					 * .lastName(passergersDTO.getLastName()) .gender(passergersDTO.getGender())
					 * .mobileNumber(passergersDTO.getMobileNumber()) .bookingInformation(findById)
					 * .check_In(Check_In.builder() .gateNumber(gatenumber())
					 * .seatNumber(seatnumber()) .build()) .build();
					 */
					System.out.println(list+"============================================");
					List<Passengers> saveAll = passergersRepository.saveAll(list);
					if (saveAll.size()>0) {
						return 1;
					} else {
                     return 0;
					}
				
				} else {
					return 0;
				}
		
		
	}
	public int gatenumber() {
	Random random = new	Random();
	while (true) {
		int num=random.nextInt(10);
		if (num>0) {
			return num;
		}
	}
}
	@Autowired
	private CheckInRepository checkInRepository;
	public int seatnumber() {
		Random random = new	Random();
		while (true) {
			int num=random.nextInt(100);
			 			Inventory findTheSeat = checkInRepository.findTheSeat(num);
			if (findTheSeat==null) {
				return num;
			}
		}
	}
	@Override
	public int deleteAirLineById(int id) {
		// TODO Auto-generated method stub
		try {
			airLineRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	
	
	}
	@Override
	public int deleteFlightById(int id) {
		// TODO Auto-generated method stub
		FlightInfo flightInfo;
		try {
			 flightInfo = flightinfoRepository.findById(id).get();
			 AirLine airLine = flightInfo.getAirLine();
			flightinfoRepository.deleteById(id);
				System.out.println(airLine.getAirLineId()+"=====================================");
			return airLineRepository.save(AirLine.builder()
					.airLineId(airLine.getAirLineId())
					.airName(airLine.getAirName())
					.build()).getAirLineId();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		/*AirLine orElse = airLineRepository.findById(parentid).orElse(null);
		if (orElse!=null) {
			FlightInfo flightremove=null;
			for (FlightInfo flightinfo: orElse.getFlightInfo()) {
				if(flightinfo.getFlightinfotId()==(childid)) {
					flightremove=flightinfo;
					break;
				
				}
			}
			if (flightremove!=null) {
				orElse.getFlightInfo().remove(flightremove);
				airLineRepository.save(orElse);
				return 1;
			}
	}*/
		              
}
@Autowired
private AdminloginRepository  adminRepository;
	@Override
	public boolean adminlogin(String id, String password) {
		System.out.println(id+password);
		// TODO Auto-generated method stub
		try {
			System.out.println("===================== 1");
 Adminlogin selectAdminidandPassword = adminRepository.selectAdminidandPassword(id, password);
			System.out.println("===================== 2");
			if (selectAdminidandPassword!=null) {
				return true;
			} else {
				return false;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("===================== 3");
			return false;
		}
	}
}

