package com.example.bank_demo.customers.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return List.of(new SimpleGrantedAuthority(role));
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
		Set<SimpleGrantedAuthority> permissionAuthoroties = role.getPermissions().stream().map(permissions -> new SimpleGrantedAuthority(permissions.name()))
				.collect(Collectors.toSet());
		
		authorities.addAll(permissionAuthoroties);
		
		
		return authorities;
	}
	//@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
