package com.jsp.airLineProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.Passengers;
@Repository
public interface PassergersRepository  extends JpaRepository<Passengers, Integer>{

}
