package com.qa.persistence.repository;

public interface AccountRepository {
	
	String getNAccountsAlphabetical(int num);

	String getAllAccounts();

	String createAccount(String account);
	
	String getAnAccount(Long id);

	String deleteAccount(Long id);

	String updateAccount(Long id, String account);
	
	int countByName(String name);

}
