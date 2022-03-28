package FreightosAssigment;

import java.util.Scanner;



//Main class
public class MainVendingMachine {
	  private static final Scanner input = new Scanner(System.in);
	  private static final String UNVALID_CHOSENSNACK_WARNING  = "Please enter a valid snack index";
	    public static void main(String[] args) {
	    
	    	SnackMachine snackMachine = new SnackMachine();
	        Snack selectedSnack;
	        snackMachine.getUserInput().Scanner(input);
	        while (true) {
	            System.out.println("\nWelcome :) choose youre Snack and Enjoy it");
	            snackMachine.showList();
	            int userChoice = 0;
	            int exceptionFlag = 0;
	            try {
	                userChoice = Integer.parseInt(snackMachine.getUserInput().afterRead());
	            } catch (NumberFormatException e) {
	                exceptionFlag = 1;
	            }
	            if (exceptionFlag == 1 || userChoice < 0 || userChoice > 24) {
	                System.err.println(UNVALID_CHOSENSNACK_WARNING );
	                continue;
	            }
	            selectedSnack = snackMachine.chosenItem(userChoice);
	            if (selectedSnack == null) {
	                System.err.println("Sorry!the snack is sold out,please choose another one");
	            } else {
	                System.out.println("Snack is avaliable : " + selectedSnack.getSnackName() + " with price " + selectedSnack.getSnackPrice());
	                snackMachine.isDone(userChoice, selectedSnack.getSnackPrice());
	            }
	    }
	  
	    }
}
	  
	  
	  
	  
	  
	   
