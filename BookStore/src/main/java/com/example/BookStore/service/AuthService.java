package com.example.BookStore.service;

import com.example.BookStore.dto.LoginRequest;
import com.example.BookStore.dto.LoginResponse;
import com.example.BookStore.dto.RegisterRequest;
import com.example.BookStore.dto.RegisterResponse;

public interface AuthService {
	LoginResponse login(LoginRequest loginRequest);
	RegisterResponse register(RegisterRequest registerRequest);
	boolean verifyUser(String code);
}
