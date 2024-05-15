package com.example.BookStore.dto;

import lombok.Data;

@Data
public class RegisterResponse {
	private String token;
	private String error;
}
