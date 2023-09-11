package com.jsp.airLineProject.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.FlightInfo;
@Repository
public interface FlightinfoRepository extends JpaRepository<FlightInfo,Integer >{
	@Query("SELECT f1 FROM FlightInfo f1 WHERE f1.flightNumber = :number")
	FlightInfo checkFlightinfoNumber(@Param("number") String number); 
	@Query("SELECT f1 FROM FlightInfo f1 JOIN f1.flight f2 WHERE f1.flightNumber = :number AND f1.flightTime = :time AND f2.flightDate = :date")
	FlightInfo findByNumberFlightnumberTimeDate(@Param("number") String number,@Param("time")LocalTime time,@Param("date")LocalDate date);
	@Query("SELECT f1 FROM FlightInfo f1 JOIN f1.flight f2 WHERE f1.flightNumber = :number AND f1.flightTime = :time AND f2.flightDate = :date")
	List<FlightInfo> findbyNumberFlightnumberTimeDate(@Param("number") String number,@Param("time")LocalTime time,@Param("date")LocalDate date);
}
