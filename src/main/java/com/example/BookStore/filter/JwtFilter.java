package com.example.BookStore.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.BookStore.service.JwtService;
import com.example.BookStore.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	private final JwtService jwtService;
	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		final String authHeader = request.getHeader("Authorization"); 
		final String jwt;
		final String username;
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies==null) {
			// Causes the next filter in the chain to be invoked
			filterChain.doFilter(request, response);
			return;
		}

		String accessToken = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("accessToken")) {
				accessToken = cookie.getValue();
				break;
			}
		}
		
		if(accessToken == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = accessToken;
		
//		if(jwtService.isTokenExpired(jwt)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
		username = jwtService.extractUsername(jwt);
		

		if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userService.userDetailsService().loadUserByUsername(username);

			if (jwtService.isTokenValid(jwt, userDetails)) {
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				securityContext.setAuthentication(token);
				SecurityContextHolder.setContext(securityContext);
			}
			
		}
		filterChain.doFilter(request, response);
	}
}