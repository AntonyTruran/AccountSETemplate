package com.qa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountServiceImpl;
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.rest.AccountEndpoint;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountTestService {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountServiceImpl service;

	@Mock
	private AccountRepository repo;
	
	@Mock
	private JSONUtil util;

	@Before
	public void setup() {
		service.setRepo(repo);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(repo.getAllAccounts()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getAllAccounts());
	}
	
	@Test
	public void testGetAnAccount() {
		Mockito.when(repo.getAnAccount(1L)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getAnAccount(1L));
	}

	@Test
	public void testCreateAccountPass() {
		Mockito.when(util.getObjectForJSON(Mockito.anyObject(), Mockito.any())).thenReturn(new Account("","","1"));
		Mockito.when(repo.createAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.createAccount(MOCK_VALUE2));
	}
	
	@Test
	public void testCreateAccountFail() {
		Mockito.when(util.getObjectForJSON(Mockito.anyObject(), Mockito.any())).thenReturn(new Account("","","9"));
		assertEquals("{\"message\": \"not allowed to use this account number\"}", service.createAccount(MOCK_VALUE2));
	}

	@Test
	public void testDeleteMovie() {
		Mockito.when(repo.deleteAccount(1L)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.deleteAccount(1L));
	}

	@Test
	public void testCountByName() {
		Mockito.when(repo.countByName("Tony")).thenReturn(1);
		assertEquals(1, service.countByName("Tony"));
	}
	
	@Test
	public void testgetNAccountsAlphabetical() {
		Mockito.when(repo.getNAccountsAlphabetical(1)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getNAccountsAlphabetical(1));
	}
}
