package com.qa.DBtests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBTest {

	@InjectMocks
	AccountDBRepository repo;

	@Mock
	private EntityManager manager;
	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_TABLE_ROW = "[{\"firstName\":\"Tom\",\"lastName\":\"Troll\",\"accountNumber\":\"8\"}]";
	private static final String MOCK_ACCOUNT = "{\"firstName\":\"Tom\",\"lastName\":\"Troll\",\"accountNumber\":\"8\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> mockAccount = new ArrayList<Account>();
		mockAccount.add(new Account("Tom", "Troll", "8"));
		Mockito.when(query.getResultList()).thenReturn(mockAccount);
		System.out.println(repo.getAllAccounts());
		assertEquals(MOCK_TABLE_ROW, repo.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		assertEquals("{\"message\": \"account has been sucessfully added\"}", repo.createAccount(MOCK_ACCOUNT));
	}

	@Test
	public void testDeleteAccountInvalid() {
		assertEquals("{\"message\": \"no such account\"}", repo.deleteAccount(1L));
	}

	@Test
	public void testDeleteAccountValid() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		assertEquals("{\"message\": \"account has been sucessfully deleted\"}", repo.deleteAccount(1L));
	}

	@Test
	public void testCycleAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> cycledAccounts = new ArrayList<Account>();
		cycledAccounts.add(new Account("Tom", "Troll", "8"));
		Mockito.when(query.getResultList()).thenReturn(cycledAccounts);
		Assert.assertEquals(1, repo.countByName("Tom"));
	}

	@Test
	public void testGetAnAccount() {
		Account mockAccount = new Account("Tom", "Troll", "8");
		Mockito.when(manager.find(Mockito.anyObject(), Mockito.anyLong())).thenReturn(mockAccount);
		assertEquals(MOCK_ACCOUNT, repo.getAnAccount(1L));
	}

	@Test
	public void testgetNAccountsAlphabetical() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setMaxResults(Mockito.anyInt())).thenReturn(query);
		List<Account> mockAccounts = new ArrayList<Account>();
		mockAccounts.add(new Account("Tom", "Troll", "8"));
		Mockito.when(query.getResultList()).thenReturn(mockAccounts);
		assertEquals(MOCK_TABLE_ROW, repo.getNAccountsAlphabetical(1));
	}

	@Test
	public void testUpdateAccountInvalid() {
		assertEquals("{\"message\": \"no such account or you have not specified an account ID to update\"}",
				repo.updateAccount(1L, MOCK_ACCOUNT));
	}

	@Test
	public void testUpdateAccountValid() {
		String localImport = "{\"id:\":\"1L\",\"firstName\":\"Tom\",\"lastName\":\"Troll\",\"accountNumber\":\"8\"}";
		Mockito.when(manager.find(Mockito.anyObject(), Mockito.anyLong())).thenReturn(true);
		assertEquals("{\"message\": \"account has been sucessfully updated\"}", repo.updateAccount(1L, localImport));
	}
}
