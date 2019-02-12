package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.repository.AccountMapRepositoryJack;
import com.qa.util.JSONUtilJack;

public class AccountServiceTestJack {

	AccountMapRepositoryJack repo;
	private JSONUtilJack util;

	@Before
	public void setup() {
		repo = new AccountMapRepositoryJack();
		repo.createAccount(
				"{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		repo.createAccount(
				"{\"id\": 2, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123456\"}");
	}

	@Test
	public void addAccountTest() { 
		assertNotNull(repo.getAccountMap().get(1L));
	}

	@Test
	public void add2AccountsTest() {
		assertNotNull(repo.getAccountMap().get(2L));
	}

	@Test
	public void removeAccountTest() {
		repo.deleteAccount(1L);
		assertNull(repo.getAccountMap().get(1L));
	}

	@Test
	public void remove2AccountsTest() {
		repo.deleteAccount(1L);
		repo.deleteAccount(2L);
		assertNull(repo.getAccountMap().get(2L));
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		repo.deleteAccount(1L);
		repo.deleteAccount(2L);
		assertEquals("{\"message\": \"no such account\"}", repo.deleteAccount(2L));
	}

	@Test
	public void jsonStringToAccountConversionTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Test
	public void accountConversionToJSONTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		// For a later piece of functionality
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		// For a later piece of functionality
		fail("TODO");
	}

}
