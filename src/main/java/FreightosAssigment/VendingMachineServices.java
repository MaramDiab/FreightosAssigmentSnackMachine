package FreightosAssigment;
//this abstract class declare all the services that the machine offers 
//here I use generic  Abstract class so we can apply override easily for all types of objects (Snack ,drinks ...)

public abstract class VendingMachineServices<G> {
	/*get what user choose*/
	   private UserInput input= new UserInput();
	   public  UserInput getUserInput() {
		 return input;  
	   }

    private PurchaseController purchaseController = new PurchaseController();

	/*return the purchase chosen way*/
    public PurchaseController getPurchaseController() {
    	return purchaseController;
    }
    /*return the chosen item by the user*/
    public abstract G chosenItem(int key);
    
	/* show the vending machine item list*/

    public abstract void showList();
    /*check the process of buying is done successfully or not*/
    public abstract boolean isDone(int userChoice, double price);
	
	
}
