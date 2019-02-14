package com.qa.persistence.repository;

import com.qa.persistence.domain.Account;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(Long id);

	String updateAccount(Long id, String account);
	
	int countByName(String name);

	public String getAnAccount(Long id);

	String getNAccountsAlphabetical(int num);

}
