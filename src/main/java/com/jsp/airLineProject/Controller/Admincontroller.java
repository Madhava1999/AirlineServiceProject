package com.jsp.airLineProject.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FareDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.DTO.FlightInfoDTO;
import com.jsp.airLineProject.DTO.InventoryDTO;
import com.jsp.airLineProject.Service.Admin;
import com.jsp.airLineProject.Service.AdminUpdates;

import lombok.ToString;
@ToString
@Controller
public class Admincontroller {
	@Autowired
private Admin service;

	@RequestMapping("/admin")
	public String adminlogin() {
		return "adminlogin";
	}
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id,@RequestParam("password") String password,Model model) {
		boolean adminlogin = service.adminlogin(id, password);
		ModelAndView mv=new ModelAndView();
		if (adminlogin) {
			mv.setViewName("adminupdate");
			return  mv;
		} else {
			mv.setViewName("adminlogin");
		   model.addAttribute("msg", "Opps sorry....! We not Found Any Detalis Of Admin ");
			return mv;
		}
	}

	@RequestMapping("/adminupdatehome")
	public ModelAndView adminupdate() {
		ModelAndView andView =new ModelAndView();
		andView.setViewName("adminupdate");
		return andView;
	}
	
	@RequestMapping("/addNewThings")
	public String newupdates() {
		return "addNewThings";
	}

	@RequestMapping("/airline")
	public ModelAndView addAirline() {
		ModelAndView andView=new ModelAndView();
		andView.setViewName("addAirline");
		return  andView;
	}
	@GetMapping("/addairline")
	public ModelAndView addingairlineOpration(@RequestParam("airlinename")String name,Model model){
		
	   AirLineDTO airLineDTO =new AirLineDTO();
      	airLineDTO.setAirName(name);
		ModelAndView modelAndView =new ModelAndView();
		if (service.addairline(airLineDTO)!=0) {
			model.addAttribute("msg11", "Airline Details Are Added Successfully....!");
			modelAndView.setViewName("addNewThings");
			return modelAndView;
		} else {
			modelAndView.setViewName("addNewThings");
			model.addAttribute("msg12", "Already This Airline Details Are Add....!");
			return modelAndView;
 		}	
	}
	@RequestMapping("/flightinfo")
	public String flightinfo() {
		return "addflightinfo";
	}
	@GetMapping("/flightinfoOpration")
	public ModelAndView flightinfoOpration(@RequestParam("id")int id,@RequestParam("flightnumber") String number,@RequestParam("type")String type,@RequestParam("time")String time,Model model){
		System.out.println("time============================"+time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		 LocalTime timetype=LocalTime.parse(time,formatter);
	
		
	   FlightInfoDTO flightInfoDTO=new FlightInfoDTO();
	   flightInfoDTO.setFlightNumber(number);
	   flightInfoDTO.setFlightType(type);
	   flightInfoDTO.setFlightTime(timetype);
	
		ModelAndView modelAndView =new ModelAndView();
		if (  service.addFlightinfoWithAirlineId(id, flightInfoDTO)==0) {
			modelAndView.setViewName("addNewThings");
			model.addAttribute("msg11", "Already This Flightinfo Details Are presents....!");
			return modelAndView;
			
		} else {
			model.addAttribute("msg12", "Flightinfo Details Are Added Successfully....!");
			modelAndView.setViewName("addNewThings");
			return modelAndView;
 		}	
	}
	@RequestMapping("/flight")
	public String flight() {
		return "addflight";
	}
	@GetMapping("/flightOpration")
	public ModelAndView flightOpration(@RequestParam("fareid")int fareid,@RequestParam("invid")int invid,@RequestParam("flightnumber") String number,@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("date")String date,Model model){
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate parse = LocalDate.parse(date,ofPattern);
	FlightDTO flightdto=new FlightDTO();
	flightdto.setCurrentLocation(from);
	flightdto.setDesination(to);
	flightdto.setFlightDate(parse);
	flightdto.setFlightNumber(number);

		ModelAndView modelAndView =new ModelAndView();
		if (   service.addFlightwithFlightinfo(flightdto, fareid, invid)==0) {
			modelAndView.setViewName("addNewThings");
			model.addAttribute("msg11", "Opps....First Register in Flightinfo  ....!");
			return modelAndView;
			
		} else {
			model.addAttribute("msg12", "Flight Details Are Added Successfully....!");
			modelAndView.setViewName("addNewThings");
			return modelAndView;
 		}	
	}
	@RequestMapping("/fare")
	public String fare() {
		return "addfare";
	}
	@GetMapping("/fare1")
	public ModelAndView fareOpration(@RequestParam("amount")String amount,@RequestParam("currency")String currency,Model model){
		 double result = Double.parseDouble(amount);
	
	  FareDTO fareDto=new FareDTO();
	  fareDto.setAmount(result);
	  fareDto.setCurrency(currency);
		ModelAndView modelAndView =new ModelAndView();
		if (service.addFare(fareDto)!=0) {
			model.addAttribute("msg11", "Fare Details Are Added Successfully....!");
			modelAndView.setViewName("addNewThings");
			return modelAndView;
		} else {
			modelAndView.setViewName("addNewThings");
			model.addAttribute("msg12", "Fare This Airline Details Are Add....!");
			return modelAndView;
 		}	
	}
	@RequestMapping("/inventory")
	public String inventory() {
		return  "addinventory";
	}
	@GetMapping("/inventory1")
	public ModelAndView inventoryOpration(@RequestParam("count1") int count,Model model){
		
	InventoryDTO dto=new InventoryDTO();
	dto.setCount(count);
		ModelAndView modelAndView =new ModelAndView();
		if (service.addInventory(dto)!=0) {
			model.addAttribute("msg12", "inventory Details Are Added Successfully....!");
			modelAndView.setViewName("addNewThings");
			return modelAndView;
		} else {
			modelAndView.setViewName("addNewThings");
			model.addAttribute("msg11", "inventory Are not Added....!");
			return modelAndView;
 		}	
	}
//============================== upgrade =====================
	@Autowired
	private AdminUpdates adminUpdates;
	@RequestMapping("/upgradeoldDetails")
	public String upgradeOldDetails() {
		return  "upgradeOldThings";
	}
	
	@RequestMapping("/upgradeAirline")
	public String upgradeAirline() {
	return "upgradeAirline";	
	}
	@GetMapping("/upgradeairline")
	public ModelAndView upgradeairlineOpration(@RequestParam("airlinename")String name, @RequestParam("id")int id,Model model){
		
	   
		ModelAndView modelAndView =new ModelAndView();
		if (adminUpdates.updateAirLine(name, id)!=0) {
			model.addAttribute("msg12", "Airline Details Are Upgreda Successfully....!");
			modelAndView.setViewName("upgradeOldThings");
			return modelAndView;
		} else {
			modelAndView.setViewName("upgradeOldThings");
			model.addAttribute("msg11", "Airline Id not found....!");
			return modelAndView;
 		}	
	}
	@RequestMapping("/upgradeFlightinfo")
	public String upgradeFlightinfo() {
	return "upgradeFlightinfo";	
	}
	@GetMapping("/upgradeflight")
	public ModelAndView upgradeflight(@RequestParam("airid")int airid,@RequestParam("flightinfoid") int flightinfoid,@RequestParam("flightnumber") String number,@RequestParam("type")String type,@RequestParam("time")String time,Model model){
		System.out.println("time============================"+time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		 LocalTime timetype=LocalTime.parse(time,formatter);
	
		
	   FlightInfoDTO flightInfoDTO=new FlightInfoDTO();
	   flightInfoDTO.setFlightNumber(number);
	   flightInfoDTO.setFlightType(type);
	   flightInfoDTO.setFlightTime(timetype);
	
		ModelAndView modelAndView =new ModelAndView();
		if (  adminUpdates.updateFlightinfoDetails(flightinfoid, flightInfoDTO, airid)==0) {
			modelAndView.setViewName("upgradeOldThings");
			model.addAttribute("msg11", "Flightinfo Details not Upgraded....!");
			return modelAndView;
			
		} else {
			model.addAttribute("msg12", "Flightinfo Details Are Upgraded Successfully....!");
			modelAndView.setViewName("upgradeOldThings");
			return modelAndView;
 		}	
	}
}
