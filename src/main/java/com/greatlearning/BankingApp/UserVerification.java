package com.greatlearning.BankingApp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserVerification {
	Set<Customer> customerList = new HashSet<Customer>();
	Boolean userVerified = false;

	public UserVerification() {
		customerList.add(new Customer(1234, "password1", 1000));
		customerList.add(new Customer(2222, "password2", 2000));
		customerList.add(new Customer(3322, "password3", 0));
	}

	public Boolean userVerificationFn(Integer accNumber, String passwrd) {

		Set<Customer> customer = customerList.stream().filter(c -> c.getAccountNumber().equals(accNumber)).collect(Collectors.toSet());
		if(customer.size() > 0) {
			customer.forEach(data -> {
				if (data.getAccountNumber().equals(accNumber) && data.getPasswrd().equals(passwrd)) {
					userVerified = true;
				} else {
					userVerified = false;
				}
			});
			
		}else {
			userVerified = false;
		}
			
		return userVerified;

	}



	public Boolean userVerificationBasedOnAccountNum(Integer accNumber) {

		Set<Customer> customer = customerList.stream().filter(c -> c.getAccountNumber().equals(accNumber)).collect(Collectors.toSet());
		if(customer.size() > 0) {
			customer.forEach(data -> {
				if (data.getAccountNumber().equals(accNumber)) {
					userVerified = true;
				} else {
					userVerified = false;
				}
			});
			
		}else {
			userVerified = false;
		}
		return userVerified;
	}


}
