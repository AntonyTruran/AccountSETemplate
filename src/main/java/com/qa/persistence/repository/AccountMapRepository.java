package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository {

	Map<Long, Account> accountMap = new HashMap<Long, Account>();
	List<Account> accountMaps = new ArrayList<Account>();
	
	
	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}
 
	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}

	private JSONUtil util = new JSONUtil();

	public String getAllAccounts() {
		return util.getJSONForObject(accountMap.values());
	}

	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(anAccount.getId(), anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	public String deleteAccount(Long id) {
		if (accountMap.get(id) != null) {
			accountMap.remove(id);
			return "{\"message\": \"account has been sucessfully deleted\"}";
		}
		return "{\"message\": \"no such account\"}";
	}

	public String updateAccount(Long id, String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if (accountMap.get(id) != null) {
			accountMap.put(id, anAccount);
			return "{\"message\": \"account has been sucessfully updated\"}";
		}
		return "{\"message\": \"no such account\"}";
	}

	public int cycleAccount(String stringName) {
		accountMaps = accountMap.values().stream().filter(n -> n.getFirstName().equals(stringName)).collect(Collectors.toList());
		return accountMaps.size();
	}

}
