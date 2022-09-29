package com.example.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTTokenHelper  {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private String secret = "jwtsecret";
	
	// get username from tokens
	public String getUsernameFromToken(String token) {
		System.out.println("getUsrenameFromTojen "+getClaimFromToken(token,Claims::getSubject));
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	// retrive expiration from token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		
		return claimResolver.apply(claims);
	}
	// for retriving any info from token
	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		System.out.println("Claims field: "+Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody());
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	// check if the token has expired
	private Boolean isTokenExprired(String token) {
		final Date expiration  = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	// generate token
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		System.out.println("Username is : "+username);
		return (username.equals(userDetails.getUsername()) && !isTokenExprired(token));
	}
}
