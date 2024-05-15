package com.example.BookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.BookStore.filter.JwtFilter;
import com.example.BookStore.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final JwtFilter jwtFilter;
	private final UserService userService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(requests -> requests
				// auth
				.requestMatchers("/authentication","/login", "/logout",
						"/register", "/verify", "/success-register","/reset-password","/forgot-password",
						"/notify-check-email","/notify-check-email-createNewPass","/products","/products/search").permitAll()
				// home
                .requestMatchers("/").permitAll()
                // frontend
                .requestMatchers("/404", "/WEB-INF/jsp/**", "/static/**").permitAll()
                .anyRequest().authenticated()
                
        )
		.logout((logout) -> logout.deleteCookies("accessToken", "refreshToken","username"))
        .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
        )
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(AuthenticationProvider())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	return http.build();
	}
	
	@Bean
	AuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}