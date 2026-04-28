package com.example.bank_demo.customers.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.bank_demo.customers.service.*;
import com.example.bank_demo.customers.config.*;
import com.example.bank_demo.customers.entity.*;

import com.example.bank_demo.customers.repository.UserDetailsRepo;

@Component
public class AdminUserInitializer {
	


	// public CommandLiner createAdminUser()
   @Bean
	public CommandLineRunner createAdminUser(UserDetailsRepo userRepository, PasswordEncoder passwordEncoder) {

			return args -> {

			if (userRepository.findByusername("admin").isEmpty()) {

			Users admin = new Users();

			admin.setUsername("admin");

			//admin.setPassword(passwordEncoder.encode(rawPassword: "admin1234")); // Securely
			admin.setPassword(passwordEncoder.encode("admin1234")); // Securely
			
			//admin.setRole("ROLE_ADMIN");
			admin.setRole(Role.ADMIN);

			userRepository.save(admin);

			System.out.println("Default admin user created!");
			}
			
			if (userRepository.findByusername("user").isEmpty()) {

				Users admin = new Users();

				admin.setUsername("user");

				//admin.setPassword(passwordEncoder.encode(rawPassword: "admin1234")); // Securely
				admin.setPassword(passwordEncoder.encode("user1234")); // Securely
				
				//admin.setRole("ROLE_ADMIN");
				admin.setRole(Role.USER);

				userRepository.save(admin);

				System.out.println("Default user created!");
				}
			
	        };
	    }
	}
