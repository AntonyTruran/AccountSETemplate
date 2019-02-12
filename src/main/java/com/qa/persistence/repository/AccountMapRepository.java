package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository {

	Map<Long, Account> accountMap = new HashMap<Long, Account>();

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

	public int cycleAccounts(String firstName) {				
		
		List <Account> accountList = new ArrayList<Account>(accountMap.values());
		int count = 0;
		String currentName = "";

		for(int i = 0; i < accountList.size(); i++) 
		{
			currentName = accountList.get(i).getFirstName();
			if(currentName.equals(firstName))
			{
				count++;	
			}
		}
		System.out.println(count);
		return count;
	}

}
