package com.example.bank_demo.customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bank_demo.customers.repository.UserDetailsRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDetailsRepo UserDetailsRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return UserDetailsRepository.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
	}
	
	
}
