package com.mycode.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mycode.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Around("execution(* com.mycode.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceddingJoinPoint) throws Throwable {

		// print out method we are advising on
		String method = theProceddingJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @Around on method: " + method);

		// get begin timestamp
		long begin = System.currentTimeMillis();

		// now let's execute the method
		Object result = theProceddingJoinPoint.proceed();

		// get end timestamp
		long end = System.currentTimeMillis();

		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n====> Duration: " + duration / 1000.0 + " seconds");

		return result;
	}

	// add a new device for @After (finally) on the findAccounts method

	@After("execution(* com.mycode.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @Afte (finally) on method: " + method);
	}

	// add a new device for @AfterThrowing on the findAccounts method

	@AfterThrowing(pointcut = "execution(* com.mycode.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		myLogger.info("\n======>>> The exception is: " + theExc);
	}

	// add a new device for @AfterReturning on the findAccounts method

	@AfterReturning(pointcut = "execution(* com.mycode.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @AfterReturning on method: " + method);

		// print out the results of the method call
		myLogger.info("\n=====>>> result is: " + result);

		// let's post-process the data ... let's modify it :-)

		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);

		// print out the results of the method call
		myLogger.info("\n=====>>> result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		// loop through accounts

		for (Account tempAccount : result) {

			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();

			// update the name on the account
			tempAccount.setName(theUpperName);
		}
	}
}
