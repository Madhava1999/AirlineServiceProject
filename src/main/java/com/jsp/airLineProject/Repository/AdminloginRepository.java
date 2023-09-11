package com.jsp.airLineProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.Adminlogin;
@Repository
public interface AdminloginRepository  extends JpaRepository<Adminlogin,Integer>{
	@Query(" SELECT a1 FROM Adminlogin a1 WHERE a1.adminuserId = :id AND a1.password = :password")
Adminlogin selectAdminidandPassword(@Param("id")String userid,@Param("password")String password);
}
  