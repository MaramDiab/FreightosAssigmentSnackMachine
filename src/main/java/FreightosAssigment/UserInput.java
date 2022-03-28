package FreightosAssigment;

import java.util.Scanner;

public class UserInput {
//	  public Scanner getUserScanner() {
//		return userScanner;
//	}

//	public void setUserScanner(Scanner userScanner) {
//		this.userScanner = userScanner;
//	}

	private Scanner userScanner;

	    public String afterRead() {
	        return  userScanner.nextLine();
	    }

	    public void Scanner(Scanner scanner) {
	        this.userScanner = scanner;
	    }
}
