package com.example.bank_demo.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_demo.customers.entity.AuthRequest;
import com.example.bank_demo.customers.util.JWTUtil;

import io.jsonwebtoken.Jwt;


@RestController
public class AuthController {

	@Autowired
	AuthenticationManager AuthenticationManager;
	@Autowired
	JWTUtil jWTUtil;

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest AuthRequest)
	{
		try {
		AuthenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(AuthRequest.getUsername(), AuthRequest.getPassword())
				);
		
		return jWTUtil.generateToken(AuthRequest.getUsername());
		
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
