package com.example.BookStore.dto;

import lombok.Data;

@Data
public class LoginResponse {
	private String accessToken;
	private String refreshToken;
	private String role;
	private String message;
}
