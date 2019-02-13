package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountDBRepository implements AccountRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject JSONUtil util;
	
	@Transactional(REQUIRED)
	@Override
	public String createAccount(String account) {
		Account anAccount =  util.getObjectForJSON(account, Account.class);
		em.persist(anAccount);
		return "{\"message\": \"Account sucessfully added\"}";
	}

	@Override
	public String getAllAccounts() {
		   Query query = em.createQuery("SELECT a FROM Account a", Account.class);
		   
		   return util.getJSONForObject(query.getResultList());

	}

	@Transactional(REQUIRED)
	@Override
	public String deleteAccount(String accountNumber) {
		
		if (em.find(Account.class, findKey(accountNumber)) != null) {
		em.remove(em.find(Account.class, findKey(accountNumber)));	
		}
		return "{\"message\": \"Account sucessfully removed\"}";
	}
    
	public Account findAnAccount(String accountNumber) {
        return em.find(Account.class, findKey(accountNumber));
    }


	@Transactional(REQUIRED)
	@Override
	public String updateAccount(String accountNumber, Account account) {
		String anAccount = util.getJSONForObject(account);
		if (em.find(Account.class, findKey(accountNumber)) != null) {
		em.merge(anAccount);		}
		return "{\"message\": \"Account sucessfully updated\"}";
	}
	
	private Long findKey (String accountNumber) {
		
		return ((Account) em).getId(accountNumber);
	}
}
