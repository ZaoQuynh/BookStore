package com.example.BookStore.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	String extractUsername(String token);
	String generateToken(UserDetails userDetails);
	String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
	boolean isTokenValid(String token, UserDetails userDetails);
	boolean isTokenExpired(String token);
	String generateActivateToken(UserDetails userDetails);
}
