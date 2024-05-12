package com.example.BookStore.builder;

import java.sql.Date;

import com.example.BookStore.dto.RegisterRequest;

public class RegisterRequestBuilder {
	private RegisterRequest registerRequest;
	public RegisterRequestBuilder() {
		this.registerRequest = new RegisterRequest();
	}
	
	public RegisterRequestBuilder fullname(String fullname) {
		this.registerRequest.setFullname(fullname);
		return this;
	}
	
	public RegisterRequestBuilder username(String username) {
		this.registerRequest.setUsername(username);
		return this;
	}
	
	public RegisterRequestBuilder password(String password) {
		this.registerRequest.setPassword(password);
		return this;
	} 
	
	public RegisterRequestBuilder gender(String gender) {
		this.registerRequest.setGender(gender);
		return this;
	}
	
	public RegisterRequestBuilder birthday(Date birthday) {
		this.registerRequest.setBirthDay(birthday);
		return this;
	}
	
	public RegisterRequestBuilder email(String email) {
		this.registerRequest.setEmail(email);
		return this;
	}
	
	public RegisterRequestBuilder role(String role) {
		this.registerRequest.setRole(role);
		return this;
	}
	
	public RegisterRequest build() {
		return this.registerRequest;
	}
}
