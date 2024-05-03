package com.example.BookStore.controller;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BookStore.builder.RegisterRequestBuilder;
import com.example.BookStore.dto.LoginRequest;
import com.example.BookStore.dto.LoginResponse;
import com.example.BookStore.dto.RegisterRequest;
import com.example.BookStore.dto.RegisterResponse;
import com.example.BookStore.entity.User;
import com.example.BookStore.service.AuthService;
import com.example.BookStore.service.UserService;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final AuthService authService;
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<RegisterResponse> register(@RequestParam String fullname,
			@RequestParam String username, @RequestParam String email,
			@RequestParam String password, @RequestParam User.EGender gender,
			@RequestParam User.ERole role) {
		System.out.println("register post");
		RegisterRequest registerRequest = new RegisterRequest();
		RegisterRequestBuilder registerRequestBuilder = new RegisterRequestBuilder();
		registerRequest = registerRequestBuilder
									.fullname(fullname).username(username).email(email)
									.password(password).gender(gender).role(role).build();
		RegisterResponse registerResponse = authService.register(registerRequest);
		return ResponseEntity.ok(registerResponse);
	}
	
	@GetMapping("/notify-check-email")
	public String notifyCheckEmail() {
		return "notify-check-email";
	}
	
	@GetMapping("/verify")
	public String verifyUser(@RequestParam("code") String code) {
	    if(authService.verifyUser(code)) {
	    	System.out.println("verify get");
	    	return "success-register";
	    } else {
	    	return "404";
	    }
	}

	@PostMapping("/authentication")
	@ResponseBody
	public ResponseEntity<LoginResponse> login(@RequestParam String username, @RequestParam String password) {
		System.out.println("post");
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(username);
		loginRequest.setPassword(password);
		LoginResponse loginResponse = authService.login(loginRequest);
		// if login success
		if(loginResponse.getMessage() == null) {
			System.out.println("set cookie");
			HttpCookie cookieAccessToken = ResponseCookie.from("accessToken", loginResponse.getAccessToken())
			        .httpOnly(true)
			        .build();
			HttpCookie cookieRefreshToken = ResponseCookie.from("refreshToken", loginResponse.getRefreshToken())
			        .httpOnly(true)
			        .build();
			return ResponseEntity.ok()
			        .header(HttpHeaders.SET_COOKIE, cookieAccessToken.toString(), cookieRefreshToken.toString())
			        .body(loginResponse);
			
		}
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("vo");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
		
		return "redirect:/";
	}
	
	@GetMapping("/home")
	@ResponseBody
	public String home() {
		System.out.println("home ne");
		return "home";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
