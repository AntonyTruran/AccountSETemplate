package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;

@Path("/account")
public class AccountEndpoint {
	
	@Inject
	private AccountService service;
	
	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String createAccount(String account) {
		return service.createAccount(account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);
	}

	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/countByName/{name}")
	@GET
	@Produces({ "application/json" })
	public int countByName(@PathParam("name") String name) {
		return service.countByName(name);
	}

	@Path("/getNAccountsAlphabetical/{num}")
	@GET
	@Produces({ "application/json" })
	public String getNAccountsAlphabetical(@PathParam("num") int num) {
		return service.getNAccountsAlphabetical(num);
	}

	@Path("/getAnAccount/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAnAccount(@PathParam("id") Long id) {
		return service.getAnAccount(id);
	}

	public void setService(AccountService service) {
		this.service = service;
	}	
	

}
