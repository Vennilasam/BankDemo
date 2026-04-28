package com.example.bank_demo.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank_demo.customers.bean.Customers;

public interface CustomerRepsitory extends JpaRepository<Customers, Long> {

}
