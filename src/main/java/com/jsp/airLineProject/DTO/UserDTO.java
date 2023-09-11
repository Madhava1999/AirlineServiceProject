package com.jsp.airLineProject.DTO;

import java.time.LocalDate;

import com.jsp.airLineProject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDTO {
	private  String firstName;
	private String lastName;
	private String mobile;
	private String gender;
	private String userName;
	private String password;
	private String confirmpassword;
	private LocalDate dob;
	private String email;
}
