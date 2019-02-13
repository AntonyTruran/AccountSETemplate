package com.qa.persistence.repository;

import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;;

@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository{

	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	private JSONUtil util = new JSONUtil();
	
	@Override
	public String getAllAccounts() {
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM ACCOUNT a ORDER BY a.id", Account.class); 
		return query.getResultList().toString();
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\" : \"An account had been added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		if( manager.find(Account.class, id) != null)
			{
				manager.remove(id);
			}		
		return "{\"message\" : \"This account has been removed\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if(manager.find(Account.class, id) != null)
			{
				manager.remove(manager.find(Account.class, id));
				manager.merge(anAccount);
				return "{\"message\" : \"This account has been updated\"}";
			}
	
		return "{\"message\" : \"This account soes not exist\"}";
	}

	@Override
	public int countByName(String name) {
		Query query = manager.createQuery("Select a FROM a");
		return ((List<Account>) query.getResultList()).stream().filter(x -> x.getFirstName().equals(name)).collect(Collectors.toList()).size();
	}

}
