package com.jsp.airLineProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.jsp.airLineProject.entity.BookingInformation;

@Repository
public interface BookingInformationRepository  extends JpaRepository<BookingInformation, Integer>{
	@Query("SELECT b1  "
			+ "FROM BookingInformation b1 "
			+ " WHERE b1.bookintId = :book")
	BookingInformation findByBookingDetailsId(@Param("book") int Id);
}
