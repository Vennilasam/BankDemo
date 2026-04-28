package com.example.bank_demo.customers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank_demo.customers.entity.Users;

public interface UserDetailsRepo extends JpaRepository<Users, Long>{
	
	Optional<Users> findByusername(String username);

}
