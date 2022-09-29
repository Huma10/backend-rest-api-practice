package com.example.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. get token from header
		String requestToken = request.getHeader("Authorization");
		
		// 2. token will start from bearer
		System.out.println("Request Token: "+requestToken);
		
		String username = null;
		
		String jwtToken = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			jwtToken = requestToken.substring(7);
			System.out.println("token after removing bearer: "+jwtToken);
			try {
				username = jwtTokenHelper.getUsernameFromToken(jwtToken);
				System.out.println("Username in doFilterInternal: "+username);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT token expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid JWT");
			}
			
		}else {
			System.out.println("It does not starts with Bearer");
		}
		System.out.println("SecurityContextHolder.getContext(): "+SecurityContextHolder.getContext().getAuthentication());
		// once we get the token then we will validate
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			System.out.println("Inside validate>>>>>>>>>>>>");
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtTokenHelper.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationTokenUsernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null ,userDetails.getAuthorities());
				authenticationTokenUsernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationTokenUsernamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid JWT Token");
			}
		}else {
			System.out.println("Username is null or context is null");
		}
		filterChain.doFilter(request, response);
	}

}
