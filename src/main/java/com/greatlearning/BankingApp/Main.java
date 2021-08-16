package com.greatlearning.BankingApp;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args){
		
		/* valid account number and passwords are - [1234 , password1] [2222 , password2] */
    boolean valid = false; 

    do { 
    	Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Bank account number - ");
		String accNumber = sc.nextLine();
//		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the password -  ");
		String passwrd = sc.nextLine();
		
		BankOperations bankOperation = new BankOperations();
		UserVerification userVerify = new UserVerification();
		Boolean verifiedUser = userVerify.userVerificationFn(accNumber, passwrd);
		if (verifiedUser) {
			valid = true;
			bankOperation.InitateBanking(accNumber);
		} else {
			valid = false;
			InvalidBankTransaction invalidWithDraw = new InvalidBankTransaction("oops! Unsuccessful login attempt.Please try again..");
			System.out.println(invalidWithDraw.getMessage());
		}
    } while(!valid); // execute untill user enter correct credentials
	}
}
