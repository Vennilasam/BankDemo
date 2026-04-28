package com.example.bank_demo.customers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.bank_demo.customers.entity.Permissions;
import com.example.bank_demo.customers.entity.Role;
import com.example.bank_demo.customers.service.CustomUserDetailsService;
import com.example.bank_demo.filters.JwtAuthFilter;

import static org.springframework.security.config.Customizer.withDefaults;


import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		 http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth->
		auth.requestMatchers("/authenticate").permitAll()
		//.requestMatchers("/customers").hasRole("ADMIN")
		//.requestMatchers("/customers").hasRole(Role.ADMIN.name())
		.requestMatchers("/h2-console/**").permitAll()
		.requestMatchers(HttpMethod.GET,"/customers/**").hasAuthority(Permissions.WEATHER_READ.name())
		.requestMatchers(HttpMethod.POST,"/customers/**").hasAuthority(Permissions.WEATHER_WRITE.name())
		.requestMatchers(HttpMethod.DELETE,"/customers/**").hasAuthority(Permissions.WEATHER_DELETE.name())
		.anyRequest().authenticated())		
		.httpBasic(withDefaults()); 
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		//daoAuthenticationProvider.setUserDetailsPasswordService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(daoAuthenticationProvider);
	}
	
}
