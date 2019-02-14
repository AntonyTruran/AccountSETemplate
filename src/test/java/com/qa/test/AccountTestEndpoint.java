package com.qa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;
import com.qa.rest.AccountEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class AccountTestEndpoint {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountEndpoint endpoint;

	@Mock
	private AccountService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getAllAccounts());
	}
	
	@Test
	public void testGetAnAccount() {
		Mockito.when(service.getAnAccount(1L)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getAnAccount(1L));
	}

	@Test
	public void testCreateAccount() {
		Mockito.when(service.createAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.createAccount(MOCK_VALUE2));
	}

	@Test
	public void testDeleteMovie() {
		Mockito.when(service.deleteAccount(1L)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.deleteAccount(1L));
	}

	@Test
	public void testCountByName() {
		Mockito.when(service.countByName("Tony")).thenReturn(1);
		assertEquals(1, endpoint.countByName("Tony"));
	}
	
	@Test
	public void testGetNAccountsAlphabetical() {
		Mockito.when(service.getNAccountsAlphabetical(1)).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getNAccountsAlphabetical(1));
	}
	
	@Test
	public void testUpdateAccount() {
		Mockito.when(service.updateAccount(1L, "Tony")).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.updateAccount(1L, "Tony"));
	}
}
