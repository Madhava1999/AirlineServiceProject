package com.jsp.airLineProject.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FareDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.DTO.InventoryDTO;
import com.jsp.airLineProject.DTO.PassergersDTO;
import com.jsp.airLineProject.DTO.UserDTO;
import com.jsp.airLineProject.Repository.AirLineRepository;
import com.jsp.airLineProject.Repository.BookingInformationRepository;
import com.jsp.airLineProject.Repository.CheckInRepository;
import com.jsp.airLineProject.Repository.FlightRepository;
import com.jsp.airLineProject.Repository.PassergersRepository;
import com.jsp.airLineProject.Repository.UserRepository;
import com.jsp.airLineProject.entity.AirLine;
import com.jsp.airLineProject.entity.BookingInformation;
import com.jsp.airLineProject.entity.Check_In;
import com.jsp.airLineProject.entity.Flight;
import com.jsp.airLineProject.entity.FlightInfo;
import com.jsp.airLineProject.entity.Inventory;
import com.jsp.airLineProject.entity.Passengers;
import com.jsp.airLineProject.entity.User;
@Service
public class UserServiceImpl implements UserService{
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
	public UserDTO userIogIn(String userid, String password) {
		// TODO Auto-generated method stub
		User checkUserDetails = userRepository.checkUserDetails(userid, password);
		if (checkUserDetails!=null) {
			UserDTO build = UserDTO.builder().lastName(checkUserDetails.getLastName()).build();
			return build ;
		} else {
			return null;
		}
		
	}
	@Override
	public boolean userRegistration(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User checkUsermobile = userRepository.checkUsermobile(userDTO.getMobile());
		if (checkUsermobile==null) {
			User user=User.builder()
					.firstName(userDTO.getFirstName())
					.lastName(userDTO.getLastName())
					.gender(userDTO.getGender())
					.email(userDTO.getEmail())
					.mobile(userDTO.getMobile())
					.userName(userDTO.getUserName())
					.password(userDTO.getPassword())
					.build();
			 System.out.println(userDTO.getConfirmpassword());
			 System.out.println(userDTO.getPassword());
			if (userDTO.getConfirmpassword().equals(userDTO.getPassword())) {
				System.out.println("=================== 1");
				userRepository.save(user);
				System.out.println("=================== 2");
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	@Override
	public boolean bookingflightTicketBynumber(List<PassergersDTO> passergersDTO, String number) {
		// TODO Auto-generated method stub
		System.out.println(passergersDTO+"++============================================");
		System.out.println("number :"+number);
		Flight findByFromTO = flightRepository.findByFlightnumber(number);
		System.out.println("================ 1");
		if (findByFromTO!=null) {
			System.out.println("================ 2");
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
			System.out.println("================ 3");
			BookingInformation save = bookingInformationRepository.save(build);
			System.out.println("================ 4");
			BookingInformation findById = bookingInformationRepository.findById(save.getBookintId()).get();
			System.out.println("================ 5");
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
			System.out.println("================ 6");
			System.out.println(list+"============================================");
			List<Passengers> saveAll = passergersRepository.saveAll(list);
			System.out.println("================ 7");
			if (saveAll.size()>0) {
				System.out.println("================ 8");
				return true;
			} else {
             return false;
			}
		
		} else {
			return false;
		}


	}
	
		              
}

