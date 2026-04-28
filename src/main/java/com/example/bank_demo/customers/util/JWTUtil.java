package com.example.bank_demo.customers.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	final String SECRET = "my-super-secret-key-that-is-long-engough-1234567890!@#";

	final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

	final long EXPIRATION_TIME = 1000 * 60 * 60;

	public String generateToken(String username) {

		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(key, Jwts.SIG.HS256)
				.compact();

	}

	public String extractUsername(String token) {		
		return extractClaims(token).getSubject();
	}
	
	public Claims extractClaims(String token)
	{
		return Jwts.parser()
			    .setSigningKey(key)
			    .build()
			    .parseClaimsJws(token)
			    .getBody();
	}
	
	
	public boolean validateToken(String username,UserDetails userDetails, String token)
	{
		
		return username.equals(userDetails.getUsername());
		
	}
	
	private boolean isTokenExpired(String token)
	{
		return extractClaims(token).getExpiration().before(new Date());
	}

}
