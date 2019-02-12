package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qa.persistence.repository.AccountMapRepository;

public class AccountMapSearch {
	
	AccountMapRepository accMap;
	
	@Test
	public void searchAccountTest() {
		accMap = new AccountMapRepository();
		accMap.createAccount(
				"{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		assertEquals(1, accMap.countAccountsWithFirstName("Phil"));
	}
	
	@Test
	public void searchAccountTest2() {
		accMap = new AccountMapRepository();
		accMap.createAccount(
				"{\"id\": 1, \"firstName\": \"Dave\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		assertEquals(1, accMap.countAccountsWithFirstName("Dave"));
	}
	@Test
	public void searchAccountTest3() {
		accMap = new AccountMapRepository();
		accMap.createAccount(
				"{\"id\": 1, \"firstName\": \"Dave\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		accMap.createAccount(
				"{\"id\": 2, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		assertEquals(1, accMap.countAccountsWithFirstName("Dave"));
	}
	@Test
	public void searchAccountTest4() {
		accMap = new AccountMapRepository();
		accMap.createAccount(
				"{\"id\": 1, \"firstName\": \"Malcolm\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		accMap.createAccount(
				"{\"id\": 2, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		accMap.createAccount(
				"{\"id\": 3, \"firstName\": \"Malcolm\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		assertEquals(2, accMap.countAccountsWithFirstName("Malcolm"));
	}

}
