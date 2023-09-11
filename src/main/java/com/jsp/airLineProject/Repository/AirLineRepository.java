package com.jsp.airLineProject.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airLineProject.entity.AirLine;

public interface AirLineRepository extends JpaRepository<AirLine, Integer>{
@Query("SELECT a1 FROM AirLine a1 WHERE a1.airLineId = :id")
AirLine findAirLineById(@Param("id") int id); 
@Query("SELECT a1 FROM AirLine a1 WHERE a1.airName = :name")
AirLine findAirLineByName(@Param("name") String name); 
@Query("SELECT a1 , fi ,f1  FROM AirLine a1 JOIN a1.flightInfo fi JOIN fi.flight f1 WHERE a1.airName = :name AND fi.flightNumber = :number AND  fi.flightTime = :time AND f1.flightDate = :date ")
AirLine findByAirlinenameFlightnumberFlightdateFlighttimings(@Param("name") String name,@Param("number") String number,@Param("time") LocalTime time,@Param("date")LocalDate date);
//@Query(" UPDATE AirLine a1 SET a1.airName = :nsme WHERE a1.airLineId = :id")
//AirLine updateAirline(@Param("name") String name,@Param("id") int id);
}
