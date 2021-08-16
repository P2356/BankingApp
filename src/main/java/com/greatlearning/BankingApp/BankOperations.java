package com.greatlearning.BankingApp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BankOperations {
	boolean append = true;
	FileHandler fh;
	Logger logger = Logger.getLogger("BankOperations");
	Scanner sc = new Scanner(System.in);
	Set<Customer> customerList = new HashSet<Customer>();
	UserVerification userVerify = new UserVerification();
	TransactionLogFile txnLogFile = new TransactionLogFile();

	public BankOperations() {
		customerList.add(new Customer(1234, "password1", 1000));
		customerList.add(new Customer(2222, "password2", 2000));
		customerList.add(new Customer(3322, "password3", 0));
	}
	
	public void InitateBanking(int accNumber) {
		Scanner input = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println("------------------------------------------");
			System.out.println(" Enter the operation you want to perform \n");
			System.out.println("1. Deposit \n");
			System.out.println("2. Withdrawl \n");
			System.out.println("3. Transfer \n");
			System.out.println("0. Logout \n");
			System.out.println("------------------------------------------");

			choice = input.nextInt();
			switch (choice) {

			case 1:
				System.out.println("Enter the amount you want to deposit:  ");
				int depositAmount = input.nextInt();
				amountDepositing(accNumber, depositAmount);
				System.out.println("Amount " + depositAmount + " deposit successfully");
				break;
			case 2:
				System.out.println("Enter the amount you want to withdrawl:  ");
				int withdrawlAmount = input.nextInt();
				amountWithdrawing(accNumber, withdrawlAmount);
				break;
			case 3:
				Random random = new Random();
				int actualOtp = random.nextInt(8000);
				System.out.println("Enter the otp:  " + actualOtp);
				int userInputOtp = input.nextInt();
				if (actualOtp == userInputOtp) {
					System.out.println("OTP verification successful!");
					System.out.println("Enter the amount : ");
					int transferAmt = input.nextInt();
					System.out.println("Enter the account no to which you want to transfer: ");
					int receivingAccounNum = input.nextInt();
					Boolean verifyUser = userVerify.userVerificationBasedOnAccountNum(receivingAccounNum);
					if(verifyUser) {
						moneyTransfer(accNumber, receivingAccounNum, transferAmt);
					}else {
						System.out.println("Invalid account number ");
					}
					
				} else {
					System.out.println("Sorry! You have entered wrong OTP");
					System.out.println();
				}
				break;

			case 0:
				System.out.println("Exiting Program.Thank you for visiting Banking app!");
				System.exit(0);
				break;
			default:
				System.out.println("This is not a valid Menu Option! Please Select Another");
				break;

			}
		}
	}


	public void amountDepositing(int accNo, int depositAmount) {
		customerList.stream().filter(c -> c.getAccountNumber().equals(accNo)).collect(Collectors.toList())
				.forEach(data -> {
					try {
						int currentBalance = data.getBalance();
						int updatedBalance = currentBalance + depositAmount;
						/* i have chosen set data structure so it will not accept duplicate */
						data.setBalance(updatedBalance);
						customerList.add(data);
						displayUpdatedBalance(accNo, currentBalance, updatedBalance);
						String message = "My Deposit Transaction!  " + "\r\n";
						txnLogFile.transactionFile(message);

					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				});

	}

	public void amountWithdrawing(int accNo, int withdrawlAmt) {
		customerList.stream().filter(c -> c.getAccountNumber().equals(accNo)).collect(Collectors.toList())
				.forEach(data -> {
					try {
						int currentBalance = data.getBalance();
	/* if sufficient balance is available then allow withdrawl else send error message */
						if (currentBalance >= withdrawlAmt) {
							int updatedBalance = currentBalance - withdrawlAmt;
							data.setBalance(updatedBalance);
							customerList.add(data);
							System.out.println("Amount " + withdrawlAmt + " withdrawl successfully");
							displayUpdatedBalance(accNo, currentBalance, updatedBalance);
						} else {
							System.out.println("Sorry! Insufficient Funds");
							System.out.println();
						}
						String message = "My Withdrawl Transaction! " + "\r\n";
						txnLogFile.transactionFile(message);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				});
	}

	public void moneyTransfer(int senderAccNum, int receipientAccNo, int transferAmt) {

		try {
			senderAccountInfo(senderAccNum, transferAmt);
			receiverAccountInfo(receipientAccNo, transferAmt);
			String message = "My Transfer Transaction! " + "\r\n";
			txnLogFile.transactionFile(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void senderAccountInfo(int senderAccNum, int transferAmt) {
		customerList.stream().filter(c -> c.getAccountNumber().equals(senderAccNum)).collect(Collectors.toList())
				.forEach(data -> {
					try {
						int currentBalance = data.getBalance();
						if(currentBalance >= transferAmt ) {
							int updatedBalance = currentBalance - transferAmt;
							data.setBalance(updatedBalance);
							customerList.add(data);
							System.out.println("Sender Account Detail - " + " " + senderAccNum + "  Previous Balance : "
									+ " " + currentBalance + "  updatedBalance : " + " " + updatedBalance);
						}else {
							System.out.println("Sorry! Insufficient Funds");
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					}

				});

	}

	private void receiverAccountInfo(int receipientAccNo, int transferAmt) {
		customerList.stream().filter(c -> c.getAccountNumber().equals(receipientAccNo)).collect(Collectors.toList())
				.forEach(data -> {
					try {
						int currentBalance = data.getBalance();
						int updatedBalance = currentBalance + transferAmt;
						data.setBalance(updatedBalance);
						customerList.add(data);
						System.out
								.println("Receiver Account Detail - " + " " + receipientAccNo + "  Previous Balance : "
										+ " " + currentBalance + "  updatedBalance : " + " " + updatedBalance);

					} catch (SecurityException e) {
						e.printStackTrace();
					}

				});
	}

	private void displayUpdatedBalance(int accNo, int currentBalance, int updatedBalance) {
		System.out.println("For Account number - " + " " + accNo + " Previous Balance : " + " " + currentBalance
				+ " updatedBalance : " + " " + updatedBalance);
	}

}
