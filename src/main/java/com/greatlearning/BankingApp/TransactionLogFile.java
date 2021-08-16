package com.greatlearning.BankingApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionLogFile {
	
	public void transactionFile(String message) throws IOException {
		File myObj = new File("transactions.txt");
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		}
		FileWriter myWriter = new FileWriter("transactions.txt", true);
		myWriter.write(message);
		myWriter.close();
	}

}
