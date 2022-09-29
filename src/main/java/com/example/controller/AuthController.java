package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.APIException;
import com.example.payloads.JWTAuthRequest;
import com.example.payloads.JWTAuthResponse;
import com.example.security.CustomUserDetailsService;
import com.example.security.JWTTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest jwtAuthRequest) throws Exception {
		authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

		String generateToken = jwtTokenHelper.generateToken(userDetails);

		JWTAuthResponse authResponse = new JWTAuthResponse();
		authResponse.setToken(generateToken);
		return new ResponseEntity<JWTAuthResponse>(authResponse, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid details");
			throw new APIException("Invalid username password");
		}
		
	}
}
