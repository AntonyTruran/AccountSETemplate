package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService{

	@Inject
	AccountRepository repo;
	
	@Inject 
	JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if(anAccount.getAccountNumber().equals("9"))
		{
			return "{\"message\" : \"This account is blocked\"}";
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

}
