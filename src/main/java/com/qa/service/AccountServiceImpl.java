package com.qa.service;

import java.util.List;

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
		Account newAccount = util.getObjectForJSON(account, Account.class);
		if (newAccount.getAccountNumber().equals(9L)) {
			return"{\"message\":\"this account is blocked\"}";
		}
//		List<Account> current = repo.getAllAccounts(); 
		// loop through all accounts to compare .getAccountNumber() to the account number
		//inputed to check that it is unique 
		return null;
	}

	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		return repo.deleteAccount(id);
	}

	@Override
	public String updateAccount(Long id, String account) {
		Account updator = util.getObjectForJSON(account, Account.class);
		if (updator.getId() != id) {
			return "{\"message\":\"account id must not be changed\"}";
		}
		return repo.updateAccount(id, account);
	}

	@Override
	public int countByName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getAnAccount(Long id) {
		return repo.getAnAccount(id);
	}

	@Override
	public String getNAccountsAlphabetical(int num) {
		return repo.getNAccountsAlphabetical(num);
	}

}
