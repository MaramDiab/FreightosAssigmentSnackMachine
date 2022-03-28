package FreightosAssigment;

public class SnackMachine extends VendingMachineServices<Snack> {
//	Icreate a class of type Snack extends the abstract super  and implement all its abstract methods
	
	//get the snacks from the snack store
	private SnackStore snackStore;
	//create a constructor that create the snack store so we can reach the snacks
	public SnackMachine() {
		snackStore=new SnackStore();
	}
//retrive the chosen snack by the user
	@Override
	public Snack chosenItem(int key) {
		// TODO Auto-generated method stub
		//check if the selected snack is available
		if(snackStore.isAvailable(key))return snackStore.getSnack(key);
		//if the selected snack is ran out it return null its empty
		return null;
	}
//show the snack list that the machine offer
	@Override
	public void showList() {
		// TODO Auto-generated method stub
		snackStore.showSLists();
		
	}
//if the payment is done then decrase the amount of the chosen snack by one
//then update the amount of money in the card or calculate the change depends on the price
public void deliver(int key) {
	snackStore.deliverSnack(key);
}
	@Override
	public boolean isDone(int userChoice, double price) {
		// TODO Auto-generated method stub
        if (doPayment(price)) {
        	chosenItem(userChoice);
            dispenseChanges();
            return true;
        }
		return false;
	}

	   private void dispenseChanges() {
		   getPurchaseController().dispenseChange();
	    }
	   private boolean doPayment(double price) {
	        return  getPurchaseController().doPayment(getUserInput(), price);
	    }
}
