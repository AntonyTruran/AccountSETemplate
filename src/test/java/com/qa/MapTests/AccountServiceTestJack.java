package com.qa.MapTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepositoryJack;
import com.qa.util.JSONUtilJack;

public class AccountServiceTestJack {

	AccountMapRepositoryJack repoJack;
	private JSONUtilJack utilJack;

	@Before
	public void setup() {
		utilJack = new JSONUtilJack();
		repoJack = new AccountMapRepositoryJack();
		repoJack.createAccount(
				"{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}");
		repoJack.createAccount(
				"{\"id\": 2, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123456\"}");
		repoJack.createAccount(
				"{\"id\": 3, \"firstName\": \"Ella\", \"lastName\": \"Jerry\", \"accountNumber\": \"123411\"}");
		repoJack.createAccount(
				"{\"id\": 4, \"firstName\": \"Sam\", \"lastName\": \"Fisher\", \"accountNumber\": \"347432\"}");
	}

	@Test
	public void addAccountTest() {
		assertNotNull(repoJack.getAccountMap().get(1L));
	}

	@Test
	public void add2AccountsTest() {
		assertNotNull(repoJack.getAccountMap().get(2L));
	}

	@Test
	public void removeAccountTest() {
		repoJack.deleteAccount(1L);
		assertNull(repoJack.getAccountMap().get(1L));
	}

	@Test
	public void remove2AccountsTest() {
		repoJack.deleteAccount(1L);
		repoJack.deleteAccount(2L);
		assertNull(repoJack.getAccountMap().get(2L));
	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		repoJack.deleteAccount(1L);
		repoJack.deleteAccount(2L);
		assertEquals("{\"message\": \"no such account\"}", repoJack.deleteAccount(2L));
	}

	@Test
	public void accountUpdateTest() {
		repoJack.updateAccount(1L, "{\"firstName\": \"Josh\"}");
		assertEquals("Josh", repoJack.getAccountMap().get(1L).getFirstName());
	}
	
	@Test
	public void jsonStringToAccountConversionTest() {
		assertEquals(repoJack.getAccountMap().get(1L).getAccountNumber(), utilJack.getObjectForJSONJack("{\"id\": 1, \"firstName\": \"Phil\", \"lastName\": \"Jerry\", \"accountNumber\": \"102836\"}", Account.class).getAccountNumber());
	}

	@Test
	public void accountConversionToJSONTest() {
		assertEquals("{\"id\":1,\"firstName\":\"Phil\",\"lastName\":\"Jerry\",\"accountNumber\":\"102836\"}", utilJack.getJSONJackForObject(repoJack.getAccountMap().get(1L)));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		assertEquals(0, repoJack.countByName("John"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		assertEquals(1, repoJack.countByName("Phil"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenTwo() {
		assertEquals(2, repoJack.countByName("Ella"));
	}
}
