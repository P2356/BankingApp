package com.greatlearning.BankingApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionLogFile {
	
	public void transactionFile(String message,String accNum,int operationAmt,int balance,String operationName) throws IOException {
		File myObj = new File("transactions.txt");
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		}
		FileWriter myWriter = new FileWriter("transactions.txt", true);
		String fullMessage = message + "Customer Info - Account Number: " + accNum + " " + operationName + 
				" Amt : " + operationAmt + " Total Balance : " + balance + "\r\n";
		myWriter.write(fullMessage);
		myWriter.close();
	}
	
	public void readLogs(String accNumber) {
		
		File myObj = new File("transactions.txt");
		Path path = Paths.get("transactions.txt");
	    if (myObj.exists()) {
	    	List<String> stringList = getLinesThatContain(path, accNumber);
	    	for(String item:stringList) {
	    		System.out.println(item + "\r\n");
	    	}
	    } else {
	      System.out.println("The file does not exist.");
	    }
	}

	private List<String> getLinesThatContain(Path path, String accNum) {
		List<String> filteredList = null;

        try(Stream<String> stream = Files.lines(path)){
            // Filtering logic here
             filteredList = stream.filter(line -> line.contains(accNum))
                                  .collect(Collectors.toList());

        } catch (IOException ioe) {
            // exception handling here
        }
        return filteredList;
	}

}
