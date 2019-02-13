package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.inject.Alternative;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtilJack;

@Alternative
public class AccountMapRepositoryJack implements AccountRepository {

	Map<Long, Account> accountMap = new HashMap<Long, Account>();

	public Map<Long, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Long, Account> accountMap) {
		this.accountMap = accountMap;
	}

	private JSONUtilJack util = new JSONUtilJack();

	public String getAllAccounts() {
		return util.getJSONJackForObject(accountMap.values());
	}

	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSONJack(account, Account.class);
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
		Account anAccount = util.getObjectForJSONJack(account, Account.class);
		if (accountMap.get(id) != null) {
			accountMap.put(id, anAccount);
			return "{\"message\": \"account has been sucessfully updated\"}";
		}
		return "{\"message\": \"no such account\"}";
	}
	
	public int countByName(String name) {
		return accountMap.values().stream().filter(n -> n.getFirstName().equals(name)).collect(Collectors.toList()).size();
	}

	public String getNAccountsAlphabetical(int num) {
		return null;
	}

	public String getAnAccount(Long id) {
		return null;
	}
}
