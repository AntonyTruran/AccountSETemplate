package com.qa.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
public class AccountTestRepo {

	@InjectMocks
	private AccountDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"Ella\",\"lastName\":\"Smith\",\"accountNumber\":\"123456\"},"
			+ "{\"firstName\":\"Tony\",\"lastName\":\"Dead\",\"accountNumber\":\"654321\"}]";
	
	private static final String MOCK_DATA_ARRAY_2 = "[{\"firstName\":\"Ella\",\"lastName\":\"Smith\",\"accountNumber\":\"123456\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"Ella\",\"lastName\":\"Smith\",\"accountNumber\":\"123456\"}";

	private List<Account> accounts;
	
	@Before
	public void setup() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account("Ella", "Smith", "123456"));
		accounts.add(new Account("Tony", "Dead", "654321"));
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}
	
	@Test
	public void testGetNAccountsAlphabetical() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setMaxResults(Mockito.anyInt())).thenReturn(query);
		accounts.remove(1);
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(MOCK_DATA_ARRAY_2, repo.getNAccountsAlphabetical(1));
	}
	
	@Test
	public void testGetAnAccount() {
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(accounts.get(0));
		assertEquals(MOCK_OBJECT, repo.getAnAccount(1L));
	}
	
	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		assertEquals("{\"message\": \"account has been sucessfully added\"}", reply);
	}
	
	@Test
	public void testDeleteAccountFailed() {
		String reply = repo.deleteAccount(10L);
		assertEquals("{\"message\": \"no such account\"}", reply);
	}
	
	@Test
	public void testDeleteAccount() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		String reply = repo.deleteAccount(1L);
		assertEquals("{\"message\": \"account has been sucessfully deleted\"}", reply);
	}
	
	@Test
	public void testCountByName() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(1, repo.countByName("Tony"));
	}
	
	@Test
	public void testUpdateAccountPart1() {
		String account = "{\"accountNumber\":\"111111\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(accounts.get(0));
		assertEquals("{\"message\": \"account has been sucessfully updated\"}", repo.updateAccount(1L, account));
	}
	
	@Test
	public void testUpdateAccountPart2() {
		String account = "{\"firstName\":\"Josh\",\"lastName\":\"Angry\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(accounts.get(0));
		assertEquals("{\"message\": \"account has been sucessfully updated\"}", repo.updateAccount(1L, account));
	}
	
	@Test
	public void testUpdateAccountFailed() {
		String account = "{\"firstName\":\"Josh\",\"lastName\":\"Angry\",\"accountNumber\":\"111111\"}";
		assertEquals("{\"message\": \"no such account\"}", repo.updateAccount(5L, account));
	}
	
}
