package com.greatlearning.BankingApp;

public class Customer {
	private String accountNumber;
	private String passwrd;
	private Integer balance;
	
	public Customer(String accountNumber, String passwrd, Integer balance) {
		this.accountNumber = accountNumber;
		this.passwrd = passwrd;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPasswrd() {
		return passwrd;
	}
	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
		

}
