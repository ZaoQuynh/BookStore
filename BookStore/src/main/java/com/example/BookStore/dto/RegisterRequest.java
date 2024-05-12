package com.example.BookStore.dto;

import java.sql.Date;


import lombok.Data;

@Data
public class RegisterRequest {
	private String fullname;
	private String username;
	private String password;
	private String gender;
	private Date birthDay;
	private String email;
	private String role;
}