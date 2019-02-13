package com.qa.buisness;

import com.qa.persistence.domain.Account;

public interface AccountService {

		String getAllAccounts();

		String createAccount(String account);

		String deleteAccount(String accountNumber);

		String updateAccount(String accountNumber, Account account);
		
		Account findAnAccount(String accountNumber);

	
}
