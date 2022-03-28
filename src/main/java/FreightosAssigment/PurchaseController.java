package FreightosAssigment;
/*
 * This class is  to control the purchase process by the customer 
 * like money store,changes left and the chosen way of purchase by the customer
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PurchaseController {
	
	//map to store all cards from file
private final List<CreditCard> cardsContainer = new ArrayList<>();
private File cardFile = new File("CreditCards.txt");
private void fillCreditCards() {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(cardFile))) {
        String line;
        String[] splitLine;
        while ((line = bufferedReader.readLine()) != null) {
            splitLine = line.split(" ");
            cardsContainer.add(new CreditCard(splitLine[0], Double.parseDouble(splitLine[1])));
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
}


//map to store money double (money slot) integer (key )
private final Map <Double,Integer> moneyContainer= new HashMap<>();
/*create a copy of moneycontainer that will handle all the updates of the purchasing
from accept the payment or not to if there is an enough changes */
private Map<Double, Integer> moneyChanges;
//fill the container of money coins and notes
public void fillMoney() {
	moneyContainer.put(.10, 3);//you have 3 of 10c
	moneyContainer.put(.20, 4);
	moneyContainer.put(.50, 8);
	moneyContainer.put(1.0, 2);	
	moneyContainer.put(50.0, 3);//you have 3 notes of 50$ and so on
	moneyContainer.put(20.0, 2);
//  System.out.println(moneyContainer.get(50.0));
	moneyChanges = new HashMap<>(moneyContainer);
}


//create the constructor hat fill money and cards when we create an object from the class
public PurchaseController() {
	fillCreditCards();
	fillMoney();
	
}
//map contain change to return to user money (double)how much money (int)
private Map<Double, Integer> updatedChanges = new HashMap<>();

//create getters for all these mapscontainers 

public Map<Double, Integer> getUpdatedChanges() {
	return updatedChanges;
}

public List<CreditCard> getCardsContainer() {
	return cardsContainer;
}


public Map<Double, Integer> getMoneyContainer() {
	return moneyContainer;
}


public Map<Double, Integer> getMoneyChanges() {
	return moneyChanges;
}
/* flow of the payment method 
 *(1) display the ways of payment available to users 
 * so the user chose an index of the payment method by cash or by credit
 * /
 */
public void displayPayments() {
    System.out.println("Please Enter the way of Payment");
    System.out.println("1-CreditCard");
    System.out.println("2-Cash(Notes&Coins)");
    //if user change his mind he can cancelled the payment and start over.
    System.out.println("3-Cancel");
}

/*
 * (2)if user choose the credit card way 
 * we must check if the card is available
 * */
public boolean isCardAvailable(String cardId) {
	//check the card container if the user card id is one of the available credit cards 
    for (CreditCard userCard : cardsContainer) {
        if (userCard.getCardId().equals(cardId))
            return true;
    }
    return false;
}
/*
 * after check the availability of the card 
 * check if the card contain a sufficient amount of money to buy the snack
 * */
public boolean payWithCard(String cardId, double price) {
    for (CreditCard userCard : cardsContainer) {
        if (isCardAvailable(userCard.getCardId())) {
        	//if the amount could by the snack return true else return false
            if (price <= userCard.getAmount()) {
            	userCard.updateAmount(price);
                return true;
            }
            return false;
        }
    }
    return false;
}
/*if user choose to buy the snack by money 
 * 1-dispense changes to customer if any exists.
 */


public void dispenseChange() {
    if (updatedChanges.size() == 0) {
        System.out.println(" No change to return");
        return;
    }
    moneyContainer.clear();
    moneyContainer.putAll(moneyChanges);
    System.out.println("Please take your change : " + displayReturnedMoney(updatedChanges));
}
/*
display the returned money to user in readable way
 
 */
private String displayReturnedMoney(Map<Double, Integer> returnedMoney) {
    StringBuilder displayResult = new StringBuilder();
    for (Map.Entry<Double, Integer> entry : returnedMoney.entrySet()) {
        for (int index = 0; index < entry.getValue(); index++) {
        	displayResult.append(entry.getKey());
        	displayResult.append("$ , ");
        }
    }
    return displayResult.toString();
}

/*
 * This method will add all the money entered by the customer and return one accumulated value.
 */
private BigDecimal getAccumulatedMoney(Map<Double, Integer> enteredMoneyMap) {
    BigDecimal accumulatedMoney = new BigDecimal(0);
    for (Map.Entry<Double, Integer> entry : enteredMoneyMap.entrySet()) {
        accumulatedMoney = accumulatedMoney.add(BigDecimal.valueOf(entry.getKey()).multiply(BigDecimal.valueOf(entry.getValue())));
    }
    return accumulatedMoney;
}

/*
 * This method will decrement localMoneyStore and will increment totalChange Map.
 */
private BigDecimal update(BigDecimal amount, double money) {
    //decrement localMoneyStore
    int count =  moneyChanges.get(money) - 1;
    if (count == 0) {
    	 moneyChanges.remove(money);

    } else {
       moneyChanges.put(money, count);
    }
    moneyChanges.merge(money, 1, Integer::sum);
    return amount.subtract(BigDecimal.valueOf(money));
}
/**
 * This method will use greedy algorithm to check if there is a sufficient change
 * or not.If yes,it will update totalChangeMap(represent change amount with
 * machine accepted money).
 *
 * @param amount is the change(accumulated - amount).
 */

private void updateTotalChangeMap(BigDecimal amount) {
    while (amount.compareTo(BigDecimal.valueOf(0)) > 0) {
        if (amount.compareTo(BigDecimal.valueOf(50.0)) >= 0 && moneyChanges.containsKey(50.0)) {
            amount = update(amount, 50.0);

        } else if (amount.compareTo(BigDecimal.valueOf(20)) >= 0 && moneyChanges.containsKey(20.0)) {
            amount = update(amount, 20.0);

        } else if (amount.compareTo(BigDecimal.valueOf(1)) >= 0 && moneyChanges.containsKey(1.0)) {
            amount = update(amount, 1);

        } else if (amount.compareTo(BigDecimal.valueOf(0.50)) >= 0 && moneyChanges.containsKey(0.50)) {
            amount = update(amount, 0.50);

        } else if (amount.compareTo(BigDecimal.valueOf(0.20)) >= 0 && moneyChanges.containsKey(0.20)) {
            amount = update(amount, 0.20);
        } else if (amount.compareTo(BigDecimal.valueOf(0.10)) >= 0 && moneyChanges.containsKey(.10)) {
            amount = update(amount, .10);
        } else {
        	moneyChanges.clear();
            return;
        }
    }
}
/**
 * This method will call updateTotalChangeMap in case accumulatedMoney >price.
 *
 * @param enteredMoneyMap entered money by the customer,key is the money itself
 *                        value is the count of the money.
 * @param price           selected snack price.
 * @return false if there is no sufficient change.
 */
private boolean collectChange(Map<Double, Integer> enteredMoneyMap, double price) {
    BigDecimal balance = getAccumulatedMoney(enteredMoneyMap);
    BigDecimal cost = BigDecimal.valueOf(price);
    BigDecimal changeAmount = (balance.subtract(cost));
    if (changeAmount.compareTo(BigDecimal.valueOf(0)) == 0) {
        System.out.println("Your payment has been performed successfully\uD83C\uDF89\uD83C\uDF89");
        moneyContainer.clear();
        moneyContainer.putAll(moneyChanges);
        return true;
    }
    updateTotalChangeMap(changeAmount);
    if (updatedChanges.size() == 0) {
    	moneyChanges.clear();
    	moneyChanges.putAll(moneyContainer);
        System.out.println("Sorry!Not_Sufficient_Change.This is the money you entered, please take them " + displayReturnedMoney(enteredMoneyMap));
        System.out.println("Please choose another Snack");
        return false;
    }
    System.out.println("Your payment has been performed successfully\uD83C\uDF89\uD83C\uDF89");
    return true;
}
/**
 * This method will execute payment based on the customer preferred method,
 * 1 for card, 2 for coin/notes.
 *
 * @param keypad        keypad of the SnackVendingMachine.
 * @param paymentChoice customer preferred payment method.
 * @param price         price of selected snack.
 * @return true if payment process is successful,false otherwise.
 */
private boolean executePaymentMethod(UserInput keypad, int paymentChoice, double price) {
    switch (paymentChoice) {
        /*
            -if customer entered wrong card id,he can continue trying until he entered the correct id or
            he can press enter to refer to payment methods .
            -if card don't have enough balance,vending machine will forward customer to payment methods.
        */
        case 1:
           updatedChanges.clear();
            System.out.println("You chose the creditCard payment method");
            while (true) {
                System.out.println("Please Enter your cardId or press ENTER to cancel");
                String cardId = keypad.afterRead();
                //check if the user press enter or not
                if (!cardId.equals("")) {
                    if (!isCardAvailable(cardId)) {
                        System.out.println("Sorry!Your credit card id is not valid, please try again or press ENTER to cancel");
                        continue;
                    }
                    if (payWithCard(cardId, price)) {
                        System.out.println("Your payment has been performed successfully\uD83C\uDF89\uD83C\uDF89");
                        return true;
                    }
                    System.out.println("Sorry!Your balance is not enough to buy this item." +
                            "Please use another method to pay or cancel the request");
                }
                return doPayment(keypad, price);
            }
            /*
            -if customer entered unaccepted money,machine will not accept them and
            will return the unaccepted money to customer and wil ask the customer to enter valid money.
            -if payment is not sufficient,machine will forward customer to payment
            methods and return the money he entered.
            -if there is no sufficient change,machine will ask customer to choose another snack.
             */
        case 2:
            /*to store customer entered money,key is the money itself,value
            is the amount of the money.
             */
            Map<Double, Integer> enteredMoneyMap = new HashMap<>();
            System.out.println("You chose the coin/Notes payment method");
            System.out.println("Please start entering your coins or bank notes in dollar\n" +
                    "When you finish please press ENTER\n");
            while (true) {
                String money = keypad.afterRead();
                //if customer press enter-->he finish inserting money to the machine.
                if (money.equals("")) {
                	 updatedChanges.clear();
                    //if money entered by customer > snack price,calculate change if any exists.
                    if (getAccumulatedMoney(enteredMoneyMap).compareTo(BigDecimal.valueOf(price)) >= 0) {
                        return collectChange(enteredMoneyMap, price);
                    }
                    System.out.println("Sorry!Your payment is not enough to buy this item.");
                    System.out.println("This is the money you entered, please take them " + displayReturnedMoney(enteredMoneyMap));
                    moneyChanges.clear();
                    moneyChanges.putAll(moneyContainer);
                    return doPayment(keypad, price);
                }
                //enteredMoney by customer.
                double enteredMoney;
                try {
                    enteredMoney = Double.parseDouble(money);

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid money");
                    continue;
                }
                //validate money entered.
                if (enteredMoney == 20.0 || enteredMoney == 50.0 || enteredMoney == 1.00 || enteredMoney == 0.50 || enteredMoney == 0.20 || enteredMoney == 0.10) {
                    enteredMoneyMap.merge(enteredMoney, 1, Integer::sum);
                    moneyChanges.merge(enteredMoney, 1, Integer::sum);
                    System.out.println("Money entered : " + getAccumulatedMoney(enteredMoneyMap));
                } else {
                    System.out.println("Sorry!Unaccepted Money entered, please enter one of .10/.20/.50/20/1/50");
                    System.out.println("This is the money you entered, please take them " + enteredMoney);
                }

            }
        case 3:
            break;
        default:
            System.out.println("Sorry!Incorrect payment method choice, please try again");
            doPayment(keypad, price);
    }
    return false;
}

/**
 * This method will display payment methods to customer,and will allow
 * him to enter his preferred payment method ,and will execute the payment
 * based on the chosen payment method.
 *
 * @param keypad keypad of the snack vending machine.
 * @param price  price of selected snack.
 * @return true if payment is successful,false otherwise.
 */
public boolean doPayment(UserInput keypad, double price) {
    displayPayments();
    int paymentChoice = 0;
    paymentChoice = Integer.parseInt(keypad.afterRead());
    return executePaymentMethod(keypad, paymentChoice, price);
}
	
	
	
	
	
}
