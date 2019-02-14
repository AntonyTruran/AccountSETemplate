package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.persistence.domain.Account;
import com.qa.service.AccountService;
import com.qa.util.JSONUtil;

@Path("/Account")
public class AccountsEndpoint {
	@Inject
	private AccountService service;
	@Inject
	private JSONUtil util;

	@Path("/getAllAccounts")
	@GET
	@Produces({ "applicaiton/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "applicaiton/json" })
	public String createAccount(String account) {
		return service.createAccount(account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "applicaiton/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);
	}

	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "applicaiton/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/countByName/{name}")
	@GET
	public int countByName(@PathParam("name") String name) {
		return service.countByName(name);
	}

	@Path("/getAnAccount/{id}")
	@GET
	public String getAnAccount(@PathParam("id") Long id) {
		return service.getAnAccount(id);
	}
	
	@Path("/getNAccountsAlphabetical/{num}")
	@GET
	public String getNAccountsAlphabetical(@PathParam("num")int num) {
		return service.getNAccountsAlphabetical(num);
	}

}
