package com.qa.service;

public interface AccountService {

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(Long id);

	String updateAccount(Long id, String account);
	
	int countByName(String name);

	public String getAnAccount(Long id);

	String getNAccountsAlphabetical(int num);
}
