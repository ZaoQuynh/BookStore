package com.example.BookStore.dto;

import java.sql.Date;

import com.example.BookStore.entity.User;

import lombok.Data;

@Data
public class RegisterRequest {
	private String fullname;
	private String username;
	private String password;
	private User.EGender gender;
	private Date birthDay;
	private String email;
	private User.ERole role;
}