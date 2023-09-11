package com.jsp.airLineProject.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.airLineProject.DTO.AirLineDTO;
import com.jsp.airLineProject.DTO.FlightDTO;
import com.jsp.airLineProject.Service.FlightService;

//@RestController
//@RequestMapping("/flight")
public class FlightController {
	@Autowired
private FlightService service;
//	@GetMapping("/flightDetails/{from}/{to}/{date}/{flightname}")
//	public ResponseEntity<List<FlightDTO>> getDetailsOfFlgihtByUseingFromTODate(@PathVariable("from") String from,@PathVariable("to")String to,@ PathVariable String date,@PathVariable("flightname")String name){
//		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate parse = LocalDate.parse(date,ofPattern);
//		
//		
//		System.out.println(from+"  "+to+"   "+date+"  "+name);
//		List<FlightDTO> findflightbyStratingAndEndingLocationsDateAndFlightName = service.findflightbyStratingAndEndingLocationsDateAndFlightName(from, to,parse, name);
//		if (findflightbyStratingAndEndingLocationsDateAndFlightName!=null) {
//			return ResponseEntity.status(HttpStatus.FOUND).body(findflightbyStratingAndEndingLocationsDateAndFlightName);
//		}
//		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
//	@GetMapping("/filghtdetails/{name}/{number}/{date}/{time}")
//	public ResponseEntity<FlightDTO> getDetailsByUsingFlightNumberDateTimeAirname(@PathVariable("name") String name,@PathVariable("number") String number,@PathVariable("date") String date,@PathVariable("time") String time){
//		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate datetype = LocalDate.parse(date,ofPattern);
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//		 LocalTime timetype=LocalTime.parse(time,formatter);
//		 FlightDTO searchFlightByUsingAirLinenameFlightnumberDateTiming = service.searchFlightByUsingAirLinenameFlightnumberDateTiming(name, number, datetype, timetype);
//		 if (searchFlightByUsingAirLinenameFlightnumberDateTiming!=null) {
//				return ResponseEntity.status(HttpStatus.FOUND).body(searchFlightByUsingAirLinenameFlightnumberDateTiming);
//			}
//			else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//			}
//	
//	}
//	@GetMapping("/filghtdetailsAmount/{number}/{date}/{time}")
//	public ResponseEntity<List<FlightDTO>> getDetailsByUsingFlightNumberDateTimeInAscendingOrder(@PathVariable("number") String number,@PathVariable("date") String date,@PathVariable("time") String time){
//		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate datetype = LocalDate.parse(date,ofPattern);
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//		 LocalTime timetype=LocalTime.parse(time,formatter);
//		 List<FlightDTO> searchFlightByUsingAirLinenameFlightnumberDateTiming = service.searchFlightByUsingFlightnumberDateTimingShowSalaryInAscendingOrder(number, datetype, timetype);
//		 if (searchFlightByUsingAirLinenameFlightnumberDateTiming!=null) {
//				return ResponseEntity.status(HttpStatus.FOUND).body(searchFlightByUsingAirLinenameFlightnumberDateTiming);
//			}
//			else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//			}
//	
//	}
//	@GetMapping("/filghtdetail/{name}/{number}/{date}/{time}")
//	public ResponseEntity<AirLineDTO> serach(@PathVariable("name") String name,@PathVariable("number") String number,@PathVariable("date") String date,@PathVariable("time") String time){
//		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate datetype = LocalDate.parse(date,ofPattern);
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//		 LocalTime timetype=LocalTime.parse(time,formatter);
//		 AirLineDTO search = service.search(name, number, datetype, timetype);
//		 if (search!=null) {
//				return ResponseEntity.status(HttpStatus.FOUND).body(search);
//			}
//			else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//			}
//	}
}
