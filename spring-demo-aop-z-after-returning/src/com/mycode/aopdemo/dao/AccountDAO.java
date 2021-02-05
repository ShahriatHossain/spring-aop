package com.mycode.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mycode.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	// add a new method: findAccounts()
	
	public List<Account> findAccounts() {
		
		List<Account> myAccounts = new ArrayList<>();
		
		// create simple accounts
		Account temp1 = new Account("Ravi", "Silver");
		Account temp2 = new Account("Ravi2", "Silver2");
		Account temp3 = new Account("Ravi3", "Silver3");
		
		// add them to our accounts list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {

		System.out.println(getClass() + "DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public boolean doWork() {

		System.out.println(getClass() + ": doWork()");
		
		return false;
	}

	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}
}
