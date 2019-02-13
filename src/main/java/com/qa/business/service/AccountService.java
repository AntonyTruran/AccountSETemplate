package com.qa.business.service;

public interface AccountService {

	String getNAccountsAlphabetical(int num);

	String getAllAccounts();

	String createAccount(String account);
	
	String getAnAccount(Long id);

	String deleteAccount(Long id);

	String updateAccount(Long id, String account);
	
	int countByName(String name);

}
