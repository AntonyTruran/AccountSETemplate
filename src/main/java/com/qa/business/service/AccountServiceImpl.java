package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService {

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
		if (util.getObjectForJSON(account, Account.class).getAccountNumber().equals("9")) {
			return "{\"message\": \"not allowed to use this account number\"}";
		}
		return repo.createAccount(account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	@Override
	public int countByName(String name) {
		return repo.countByName(name);
	}

	@Override
	public String getNAccountsAlphabetical(int num) {
		return repo.getNAccountsAlphabetical(num);
	}

	@Override
	public String getAnAccount(Long id) {
		return repo.getAnAccount(id);
	}
}
