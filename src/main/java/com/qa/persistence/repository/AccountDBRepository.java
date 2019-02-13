package com.qa.persistence.repository;

import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public Long getKey(String accountNumber) {
		return ((Account) manager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")).getId();
	}

	@Override
	public String getNAccountsAlphabetical(int num) {
		Query query = manager.createQuery("SELECT a FROM Account a ORDER BY a.firstName").setMaxResults(num);
		return util.getJSONForObject((Collection<Account>) query.getResultList());
	}

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM Account a");
		return util.getJSONForObject((Collection<Account>) query.getResultList());
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	public String getAnAccount(Long id) {
		return util.getJSONForObject(manager.find(Account.class, id));
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		if (manager.contains(manager.find(Account.class, id))) {
			manager.remove(manager.find(Account.class, id));
			return "{\"message\": \"account has been sucessfully deleted\"}";
		}
		return "{\"message\": \"no such account\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if (manager.contains(manager.find(Account.class, id)) && anAccount.getId() != null) {
			manager.merge(anAccount);
			return "{\"message\": \"account has been sucessfully updated\"}";
		}
		return "{\"message\": \"no such account or you have not specified an account ID to update\"}";
	}

	@Override
	public int countByName(String name) {
		Query query = manager.createQuery("SELECT a FROM Account a");
		return ((Collection<Account>) query.getResultList()).stream().filter(n -> n.getFirstName().equals(name))
				.collect(Collectors.toList()).size();
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
