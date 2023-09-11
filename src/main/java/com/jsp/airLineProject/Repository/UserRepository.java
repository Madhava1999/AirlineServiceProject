package com.jsp.airLineProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u1 FROM User u1 WHERE (u1.userName = :user OR u1.mobile = :user) AND u1.password = :password")
  User checkUserDetails(@Param("user")String user,@Param("password")String password);
	@Query("SELECT u1 FROM User u1 WHERE  u1.mobile = :user ")
	  User checkUsermobile(@Param("user")String user);
	
}
