package com.greatlearning.BankingApp;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args){
		
		/* valid account number and passwords are - [1234 , password1] [2222 , password2] */
		
		System.out.println("Welcome to the login Page !");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Bank account number - ");
		int accNumber = sc.nextInt();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the password -  ");
		String passwrd = scanner.nextLine();

//		user verification
		BankOperations bankOperation = new BankOperations();
		UserVerification userVerify = new UserVerification();
		Boolean verifiedUser = userVerify.userVerificationFn(accNumber, passwrd);
		if (verifiedUser) {
			bankOperation.InitateBanking(accNumber);
		} else {
			InvalidBankTransaction invalidWithDraw = new InvalidBankTransaction("oops! Unsuccessful login attempt.");
			System.out.println(invalidWithDraw.getMessage());
		}
	}

}
