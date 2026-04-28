package com.example.bank_demo.customers.controller;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomMapEditor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_demo.customers.repository.CustomerRepsitory;
import com.example.bank_demo.customers.bean.Customers;

@RestController
public class Customercontroller {
	
	@Autowired
	private CustomerRepsitory repository; 
	
	//@Autowired
//	private NocustomerException NocustomerException;
	
	
	
	@GetMapping("/customers")
	public List<Customers> getCustomer(@RequestParam Long id) throws NocustomerException 
	{
	
		List<Customers> returnCustomerList = new ArrayList<>();
		
		Optional<Customers> customer = repository.findById(id);
		//List<Customers> returnVal = new ArrayList<Customers>();
		//returnVal.add(customer);
		
		if(id == 0)
		{
			return repository.findAll();
		}
		
		else if(customer.isEmpty())
		{
			 //throw new RuntimeException(" Customer Not existed ");		
			
			//String ss = " Customer Not existed ";
			//NocustomerException NocustomerException = new NocustomerException(ss);
			//throw new NocustomerException("  Customer Not existed  ");
			List<Customers> emptyList = Collections.emptyList();
			return emptyList;
		}
		
		returnCustomerList.add(customer.get());
		return returnCustomerList;
		
	}
	
	@PostMapping("/customers")
	public void createCustomer(@RequestBody Customers customer)
	{
		repository.save(customer);
	}
	
	@PutMapping("/customers")
	public void updateCustomer(@RequestParam long id, @RequestBody Customers customer)
	{
        Optional<Customers> existingCustomer = repository.findById(id);
		
		if(existingCustomer.isEmpty())
		{
			throw new RuntimeException(" Customer Not existed ");
		}
		
		Customers Cust = existingCustomer.get();
		Cust.setName(customer.getName());
		Cust.setAccountno(customer.getAccountno());
		Cust.setBalance(customer.getBalance());
		repository.save(Cust);
	}
	
	@DeleteMapping("/customers")
	public void deletecustomer(@RequestParam long id)
	{
		repository.deleteById(id);
	}
	

}

class NocustomerException extends Exception
{
	public NocustomerException(String msg)
	{
		super(msg);
	}
}

