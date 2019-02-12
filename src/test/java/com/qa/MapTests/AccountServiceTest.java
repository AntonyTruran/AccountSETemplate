package com.qa.MapTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.persistence.repository.AccountMapRepository;
import com.qa.util.JSONUtil;


public class AccountServiceTest {

	AccountMapRepository repo;
	private JSONUtil util;

	@Before
	public void setup() {
		repo = new AccountMapRepository();
		repo.createAccount("{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		repo.createAccount("{\"id\": 2, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123456\"}");
		repo.createAccount("{\"id\": 3, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102776\"}");
		repo.createAccount("{\"id\": 4, \"firstName\": \"Ellas\", \"lastName\": \"Jerry\", \"accountNumber\": \"123876\"}");

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

	@Ignore
	@Test
	public void jsonStringToAccountConversionTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Ignore
	@Test
	public void accountConversionToJSONTest() {
		// testing JSONUtil
		fail("TODO");
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		assertEquals(0, repo.cycleAccount("Eric"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		
		assertEquals(1, repo.cycleAccount("Ella"));
		}
	

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		assertEquals(2, repo.cycleAccount("Phil"));
		}

}
