package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	AccountMapRepository repo;
	private JSONUtil util;

	@Before
	public void setup() {
		util = new JSONUtil();
		repo = new AccountMapRepository();
		repo.createAccount(
				"{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		repo.createAccount(
				"{\"id\": 2, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123456\"}");
		repo.createAccount(
				"{\"id\": 3, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123411\"}");
		repo.createAccount(
				"{\"id\": 4, \"firstName\": \"Sam\", \"lastName\": \"Fisher\", \"accountNumber\": \"347432\"}");
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
		assertEquals(util.getObjectForJSON("{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}", Account.class).getAccountNumber(), repo.getAccountMap().get(1L).getAccountNumber());
	}

	@Test
	public void accountConversionToJSONTest() {
		assertEquals(util.getJSONForObject(repo.getAccountMap().get(1L)), "{\"id\":1,\"firstName\":\"Phil\",\"lastName\":\"Jerry\",\"accountNumber\":\"102836\"}");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		assertEquals(repo.countByName("John"), 0);
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		assertEquals(repo.countByName("Phil"), 1);
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		assertEquals(repo.countByName("Ella"), 2);
	}

}
