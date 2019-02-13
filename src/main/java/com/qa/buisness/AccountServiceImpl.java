package com.qa.buisness;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService{

	@Inject
	private AccountRepository repo;

	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		
		return repo.getAllAccounts();
	}

	@Override
	public String createAccount(String account) {
		if(util.getObjectForJSON(account, Account.class).getAccountNumber().contains("9")) {
			return "{\"message\": \"Account Numbers containing 9 are forbidden\"}";
		}
		return repo.createAccount(account);
	}

	@Override
	public String deleteAccount(String accountNumber) {

		return repo.deleteAccount(accountNumber);
	}

	@Override
	public String updateAccount(String accountNumber, Account account) {
		
		return repo.updateAccount(accountNumber, account);
	}

	@Override
	public Account findAnAccount(String accountNumber) {

		return repo.findAnAccount(accountNumber);
	}
	
	
	

}
