package com.qa.persistence.domain;

public class Account {

	private Long id;
	private String firstName;
	private String lastName;
	private String accountNumber;

	public Account(Long id, String firstName, String lastName, String accountNumber) {
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName;
		this.accountNumber = accountNumber;
	}

	public Account() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
