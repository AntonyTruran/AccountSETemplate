package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository {

	private Map<Long, Account> accountMap = new HashMap<Long, Account>();

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util = new JSONUtil();
	
	@Override
	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}

	@Override
	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap.values());
	}

	@Override
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(anAccount.getId(), anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	public String deleteAccount(Long id) {
		if (accountMap.get(id) != null) {
			accountMap.remove(id);
			return "{\"message\": \"account has been sucessfully deleted\"}";
		}
		return "{\"message\": \"no such account\"}";
	}

	@Override
	public String updateAccount(Long id, String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if (accountMap.get(id) != null) {
			accountMap.put(id, anAccount);
			return "{\"message\": \"account has been sucessfully updated\"}";
		}
		return "{\"message\": \"no such account\"}";
	}
	
	private int count;
	@Override
	public String countAccountsWithFirstName(String string) {
		count = 0;
		accountMap.forEach((key,value) -> {if(value.getFirstName().equals(string)) {count++;}});
		return Integer.toString(count);
	}
}
