package FreightosAssigmentTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import java.io.FileNotFoundException;


import org.junit.Assert;
import org.junit.Test;

import FreightosAssigment.*;

/*
 * Test The Main Class Of The snackMachine
 * */
public class MainTest {

    SnackMachine snackMachine = new SnackMachine();
    Snack selectedSnack;
    /*
    test if the snack is available or soldout
     */
    @Test
    public void soldOutSnackTest() {
        //Takis count =2 index=4 price=1.60 
        Snack chosendSnack = snackMachine.chosenItem(4);
        assertTrue(chosendSnack.getSnackCount()==2);
        assertTrue(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
      

        chosendSnack = snackMachine.chosenItem(4);
        assertFalse(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
        assertTrue(chosendSnack.getSnackCount() == 1);

        chosendSnack = snackMachine.chosenItem(4);
        assertTrue(snackMachine.isDone(4, chosendSnack.getSnackPrice()));
        assertTrue(chosendSnack.getSnackCount() == 0);
        assertNull(snackMachine.chosenItem(4));
    }
    /*
     * Test the cases when user choose coins to pay
     */
    @Test
    public void byCoinTest() {
        //money enterd are accepted and theres no change
        //Bugels 1.20 2
        Snack selectedSnack = snackMachine.chosenItem(20);
        assertTrue(selectedSnack.getSnackCount() == 2);
        assertTrue(snackMachine.isDone(20, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{ 0.10=3, 0.20=4,0.50=8,1.0=2,20.0=2, 50.0=3}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
       
      //sufficient change all entered money are accepted.MMs 3.50 
        selectedSnack =snackMachine.chosenItem(17);
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{  0.20=0,0.50=3,1.0=16,20.0=2}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertEquals("{ 0.10=1, 0.20=3,0.50=2,1.0=18,20.0=2,}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 4);
      //no sufficient change
        selectedSnack =snackMachine.chosenItem(17);   
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{  0.20=0,0.50=1,1.0=11,20.0=1}", snackMachine.getPurchaseController().getMoneyContainer().toString());
       // Assert.assertEquals("{ 0.10=1, 0.20=3,0.50=2,1.0=18,20.0=2,}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 4);
        //not enough coins
        selectedSnack =snackMachine.chosenItem(17);   
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{1.0=11,20.0=1,.10=1}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 4);
        //not enough then enter enough coins
        selectedSnack =snackMachine.chosenItem(17);   
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{1.0=11,.20=12,0.50.0=10,.10=1}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 3);
        //not enough then choose card 
        selectedSnack =snackMachine.chosenItem(17);   
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{1.0=11,20.0=1,.10=1}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 2);
        //not enough coins and user cancel
        selectedSnack =snackMachine.chosenItem(17);   
        assertTrue(snackMachine.isDone(17, selectedSnack.getSnackPrice()));
        Assert.assertEquals("{1.0=1,20.0=1,.10=1}", snackMachine.getPurchaseController().getMoneyContainer().toString());
        Assert.assertTrue(snackMachine.getPurchaseController().getUpdatedChanges().size() == 0);
        assertTrue(selectedSnack.getSnackCount() == 2);
        
    }
    
    
    
    
    
}
