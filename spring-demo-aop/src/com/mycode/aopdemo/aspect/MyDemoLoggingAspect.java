package com.mycode.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void com.mycode.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(* add*(com.mycode.aopdemo.Account, ..))")
	//@Before("execution(* add*(..))")
	@Before("execution(* com.mycode.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n===== Executing @Before advice on addAccount()");
	}
}
