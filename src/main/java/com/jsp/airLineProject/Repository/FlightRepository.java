package com.jsp.airLineProject.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.Flight;
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{
	@Query("SELECT f1  "
			+ "FROM Flight f1 "
			+ " WHERE f1.flightId = :flight")
	Flight findByFlightDetailsId(@Param("flight") int Id);
	@Query("SELECT f1  FROM Flight f1 WHERE f1.flightNumber = :number")
    Flight findByFlightnumber(@Param("number")String number);
	@Query("SELECT f1  FROM Flight f1 WHERE f1.currentLocation = :from  AND  f1.desination = :to")
             Flight findByFromTO(@Param("from")String from,@Param("to") String to);
	@Query("SELECT f1  FROM Flight f1 WHERE f1.currentLocation = :from  AND  f1.desination = :to  AND f1.flightDate = :date")
 List<Flight> findByFromTOAndDate(@Param("from")String from,@Param("to") String to,@Param("date") LocalDate date);
	@Query("SELECT f1  FROM Flight f1 WHERE f1.currentLocation = :from  AND  f1.desination = :to  AND f1.flightDate = :date AND f1.flightNumber = :number")
	  Flight findByFromTOAndDateMain(@Param("from")String from,@Param("to") String to,@Param("date") LocalDate date,@Param("number")String number);
}
