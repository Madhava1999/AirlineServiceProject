package com.jsp.airLineProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airLineProject.entity.Check_In;
import com.jsp.airLineProject.entity.Inventory;

public interface CheckInRepository extends JpaRepository<Check_In, Integer> {
	@Query("SELECT s1 FROM Check_In s1  WHERE s1.seatNumber = :seat")
	Inventory findTheSeat(@Param("seat") int seat);
}
