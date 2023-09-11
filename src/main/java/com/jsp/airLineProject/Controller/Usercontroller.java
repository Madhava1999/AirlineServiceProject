package com.jsp.airLineProject.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.PassergersDTO;
import com.jsp.airLineProject.DTO.UserDTO;
import com.jsp.airLineProject.Service.FlightService;
import com.jsp.airLineProject.Service.UserService;

import lombok.ToString;

@Controller
public class Usercontroller {
	@Autowired
	private UserService service;
	@Autowired
	private FlightService flightService;
	@RequestMapping("/userlogin")
	 public String userlogin() {
		 return "userlogin";
	 }
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String userOpration(@RequestParam("id") String id,@RequestParam("password") String password,Model model) {
 UserDTO userIogIn = service.userIogIn(id, password);
		
		if (userIogIn!=null) {
		model.addAttribute("user", userIogIn);
			return  "userHome";
		} else {
			model.addAttribute("msg", "Opps sorry....! We not Found Any Detalis Of Admin ");
			return "userRegistration";
		}
	}
	
	@RequestMapping("/userRegistration")
	 public String userRegistration() {
		 return "userRegistration";
	 }
	@RequestMapping("/userRegistrationoparation")
	 public ModelAndView userRegistrationoparation(Model model,@RequestParam("firstname")String firstname,@RequestParam("lastname")String lastname,@RequestParam("date")String date,@RequestParam("gender")String gender,@RequestParam("email")String email,@RequestParam("number")String number,@RequestParam("password")String password,@RequestParam("cp")String cp) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,ofPattern);
		UserDTO dto=UserDTO.builder()
				.firstName(firstname)
				.lastName(lastname)
				.gender(gender)
				.email(email)
				.mobile(number)
				.password(password)
				.confirmpassword(cp)
				.dob(date1)
				.build();
		ModelAndView mv=new ModelAndView();
		if (service.userRegistration(dto)) {
		mv.setViewName("userlogin");
		return mv;
		} else {
			model.addAttribute("msg", "Opps sorry....! Passwords are not Matching  ");
          mv.setViewName("userRegistration");
          return mv;
		}
	 }
	@RequestMapping("/flightinfoservice")
	 public String flightinfoservice() {
		 return "flightinfoservice";
	 }
	// book flight ticket 
	@GetMapping("/bookflight")
	 public String bookflight() {
		 return "bookflight";
	 }
	private String flightnumber1;
	@GetMapping("/bookingOpration")
	public ModelAndView bookflightOpration(Model model,@RequestParam("number")String flightnumber,@RequestParam("from")String FlightStatingLocation,@RequestParam("to")String flightendingLocation,@RequestParam("date")String flightDate) {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(flightDate,ofPattern);
		System.out.println(date);
		System.out.println("number "+flightnumber);
		System.out.println("from "+FlightStatingLocation);
		System.out.println("to "+flightendingLocation);
		FlightDTO findflightbyStratingAndEndingLocationsDateAndFlightName = flightService.findflightbyStratingAndEndingLocationsDateAndFlightNumber(FlightStatingLocation, flightendingLocation, date, flightnumber);
		ModelAndView modelAndView=new ModelAndView();
		if (findflightbyStratingAndEndingLocationsDateAndFlightName==null) {
			modelAndView.setViewName("bookflight");
			model.addAttribute("msg", "Opps....Not Find Any Flight on Your Details  ....!");
			return modelAndView;
		} else {
			modelAndView.setViewName("passenger");
			model.addAttribute("number", flightnumber);
			flightnumber1=flightnumber;
			return modelAndView;
		}
	} 
	//                             passenger
	@RequestMapping("/passenger")
	 public String passenger() {
		 return "passenger";
	 }
	@RequestMapping("/passengerOpration")
	public ModelAndView passengerOpration(Model model,@RequestParam("firstname")List< String >firstname,@RequestParam("lastname")List<String> lastname,@RequestParam("gender") List<String> gender,@RequestParam("number") List<String> mobilenumber,@RequestParam("email")List<String> eamil) {
		ModelAndView  modelAndView=new ModelAndView();
		// String flightnumber = (String) model.getAttribute("number");
		//System.out.println("Flight number in controller "+flightnumber);

	   
	    int numPassengers =firstname.size();
	    int size = gender.size();
	    System.out.println("Firstname : "+numPassengers);
	    System.out.println("Gender size :"+size);
	    List<PassergersDTO> passengersList =new ArrayList<PassergersDTO>();
	    for (int i = 0; i < numPassengers; i++) {
	        // Create a new Passenger object for each passenger
	    	PassergersDTO passenger = new PassergersDTO();
	       passenger.setFirstName(firstname.get(i));
	       passenger.setLastName(lastname.get(i));
	       passenger.setEmail(eamil.get(i));
	       passenger.setMobileNumber(mobilenumber.get(i));
	     passenger.setGender(gender.get(i));
	     System.out.println("controller ========================= 1");
	   // System.out.println("Gender : "+gender.get(i));
	    System.out.println("controller ========================= 2");
	      // System.out.println("Gender "+gender.get(i));
	        // Add the passenger to the list
	       passengersList.add(passenger);
	    }
    
	if (service.bookingflightTicketBynumber(passengersList, flightnumber1)) {
    	
		modelAndView.setViewName("bookingstatus");
 		return modelAndView;
	} else {
		 model.addAttribute("msg", "Ticket not Booked");
		 modelAndView.setViewName("passenger");
			model.addAttribute("number", flightnumber1);
    	 return modelAndView;
	}

 	
	}
	@RequestMapping("/bookingstatus")
	 public String bookingstatus() {
		 return "bookingstatus";
	}
	 // ================== flight infos =====================
	@RequestMapping("/displayflightinfo")
	 public String displayflightinfo() {
		 return "displayflightinfo";
	 }
	@RequestMapping("/flightinfo1")
	 public String flightinfo1() {
		 return "flightinfo1";
	 }
	@GetMapping("/flightinfo1operation")
	 public ModelAndView flightinfo1operation(Model model,@RequestParam("name")String name,@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("date")String date) {
		ModelAndView mv=new ModelAndView();
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,ofPattern);
		List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName = flightService.findflightbyStratingAndEndingLocationsDateAndFlightName(from,to,date1,name);
		if (findflightbyStratingAndEndingLocationsDateAndFlightName!=null) {
			System.out.println("controller============================1");
			model.addAttribute("flightinfo", findflightbyStratingAndEndingLocationsDateAndFlightName);
			System.out.println("controller============================2");
			mv.setViewName("displayflightinfo");
			return mv;
		} else {
			model.addAttribute("msg", "Opps....Not Find Any Flight on Your Details  ....!");
			mv.setViewName("flightinfo1");
			return mv;
		}
		
	 }
	@RequestMapping("/flightinfo2")
	 public String flightinfo2() {
		 return "flightinfo2";
	 }
	@GetMapping("/flightinfo2operation")
	 public ModelAndView flightinfo2operation(Model model,@RequestParam("name")String name,@RequestParam("number")String number,@RequestParam("time")String time,@RequestParam("date")String date) {
		ModelAndView mv=new ModelAndView();
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,ofPattern);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		 LocalTime timetype=LocalTime.parse(time,formatter);
		 System.out.println("time :  "+timetype);
		FlightDTO searchFlightByUsingAirLinenameFlightnumberDateTiming = flightService.searchFlightByUsingAirLinenameFlightnumberDateTiming(name, number, date1, timetype);
		if (searchFlightByUsingAirLinenameFlightnumberDateTiming!=null) {
			System.out.println("controller============================1");
			model.addAttribute("flightinfo1", searchFlightByUsingAirLinenameFlightnumberDateTiming);
			System.out.println("controller============================2");
			mv.setViewName("displayflightinfo1");
			return mv;
		} else {
			model.addAttribute("msg", "Opps....Not Find Any Flight on Your Details  ....!");
			mv.setViewName("flightinfo2");
			return mv;
		}
		
	 }
	@RequestMapping("/flightinfo3")
	 public String flightinfo3() {
		 return "flightinfo3";
	 }
	@GetMapping("/flightinfo3operation")
	 public ModelAndView flightinfo3operation(Model model,@RequestParam("number")String number,@RequestParam("time")String time,@RequestParam("date")String date) {
		ModelAndView mv=new ModelAndView();
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,ofPattern);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		 LocalTime timetype=LocalTime.parse(time,formatter);
		List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName = flightService.searchFlightByUsingFlightnumberDateTimingShowSalaryInAscendingOrder(number, date1, timetype);
		if (findflightbyStratingAndEndingLocationsDateAndFlightName!=null) {
			System.out.println("controller============================1");
			model.addAttribute("flightinfo", findflightbyStratingAndEndingLocationsDateAndFlightName);
			System.out.println("controller============================2");
			mv.setViewName("displayflightinfo");
			return mv;
		} else {
			model.addAttribute("msg", "Opps....Not Find Any Flight on Your Details  ....!");
			mv.setViewName("flightinfo3");
			return mv;
		}
		
	 }
	@RequestMapping("/flightinfo4")
	 public String flightinfo4() {
		 return "flightinfo4";
	 }
	@GetMapping("/flightinfo4operation")
	 public ModelAndView flightinfo4operation(Model model,@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("date")String date) {
		ModelAndView mv=new ModelAndView();
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(date,ofPattern);
		List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName = flightService.findflightbyStratingAndEndingLocationsDate(from, to, date1);
		if (findflightbyStratingAndEndingLocationsDateAndFlightName!=null) {
			System.out.println("controller============================1");
			model.addAttribute("flightinfo", findflightbyStratingAndEndingLocationsDateAndFlightName);
			System.out.println("controller============================2");
			mv.setViewName("displayflightinfo");
			return mv;
		} else {
			model.addAttribute("msg", "Opps....Not Find Any Flight on Your Details  ....!");
			mv.setViewName("flightinfo4");
			return mv;
		}
		
	 }

	
	
}
