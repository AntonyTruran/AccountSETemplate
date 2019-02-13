package com.qa.persistence.repository;

import java.util.Map;

import com.qa.persistence.domain.Account;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(Long id);

	String updateAccount(Long id, String account);
	
	String countAccountsWithFirstName(String string);

	void setAccountMap(Map<Long, Account> accountMap);

	Map<Long, Account> getAccountMap();

}
