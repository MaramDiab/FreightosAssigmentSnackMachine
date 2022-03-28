package FreightosAssigment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;



/*SnackStore control with our Snack functionality from get to fill and update*/
public class SnackStore {
//create a hashMap then fill it with generated integer keys and Snacks from the txt file
private	Map <Integer,Snack> SnackContainer= new HashMap();
	
//constructor 
public SnackStore() {
	fillSMachine();//fills the snack machine func from the snack file
	
}
/*
 this function will read the snacks file content 
 and store them into the snackContiner
 */

public void fillSMachine() {
	
      File snacksFile = new File("snacks.txt");
      int key=0;
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(snacksFile))) {
          String line;
          String[] splitLine;
          while ((line = bufferedReader.readLine())!= null) {
              splitLine = line.split(" ");
              SnackContainer.put(key++, new Snack(splitLine[0], Double.parseDouble(splitLine[1]), Integer.parseInt(splitLine[2])));
          }
      } catch (Exception exception) {
          exception.printStackTrace();
      }
}
/*this function to retrive the
 *  snack stored in the snack container
 * */

public Snack getSnack(int key ) {
Snack snack =SnackContainer.get(key);

return snack;

}

/*
public void printSnackList() {
    int i;
    System.out.println("-------------------------------------------------------------");
    for (i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            System.out.print(i * 5 + j + "\t\t");
        }
        System.out.print("\n");
        for (int j = 0; j < 5; j++) {
            System.out.print(getItem(i * 5 + j).getName() + "\t      ");
        }
        System.out.print("\n\n");
    }
    System.out.println("-------------------------------------------------------------");
    System.out.println("Please Enter the index of your chosen snack ");
}*/
//show snack list to users function
public void showSLists() {
	Snack snacks;
	String snackName=new String();
	int snackIndex;
	//coulmns by row 
	  for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
    	  snackIndex=i* 5 + j;
	             System.out.print(snackIndex + "\t\t");//print the snack number
	                 }
	             System.out.println();
	   for (int j = 0; j < 5; j++) {
		   snacks =getSnack(i * 5 + j);
		   snackName=snacks.getSnackName();
	              System.out.print( snackName+ "\t      "  );
	                 }
	              System.out.println("\n");
	                 }
	              System.out.println("*****************************************************************************");
	              System.out.println("Please Enter the Snack index you want to choose :)");

}
/*
*this fuction to updatethe count of each snack after been chosen by user
*it decrease by one each time user chose it
*/
private int updatedCount(int key) {
	int newCount=SnackContainer.get(key).getSnackCount()-1;
	SnackContainer.get(key).setSnackCount(newCount);
	return newCount;
}
//check if the snack is available for the user
public boolean isAvailable(int key) {
	if(SnackContainer.get(key).getSnackCount()>0)return true;
	else return false ;
}
//deliver the snack to user depends on the index chosen of the snack 
//decrement the count of this snack after been delivered 
public void deliverSnack(int key) {
	updatedCount(key);
    System.out.println("Please pick your snack " +SnackContainer.get(key).getSnackName() + "");
}




}//end of SnackStore Class

