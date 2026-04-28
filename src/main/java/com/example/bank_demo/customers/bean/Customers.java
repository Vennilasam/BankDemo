package com.example.bank_demo.customers.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customers {
	
	@Id
	private long id;
	
	public Customers()
	{
		
	}
	
	public Customers(long id, String name, long accountno, long balance) {
		super();
		this.id = id;
		this.name = name;
		this.accountno = accountno;
		this.balance = balance;
	}

	private String name;
	
	private long accountno;
	
	private long balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountno() {
		return accountno;
	}

	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	public String toString()
	{
		return " Customers Id = " + id + ", Name = " + name + ", Account Number = " + accountno + ", Balance = " + balance;
	}

}
