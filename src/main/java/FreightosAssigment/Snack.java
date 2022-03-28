package FreightosAssigment;
/*
 * this class shows the main feature of the snack 
 * as discribed in the snack file
 * */
public class Snack {
	private String snackName;
	private double snackPrice;
	private int snackCount;
	

	public Snack(String snackName, double snackPrice , int snackCount ) {
		// TODO Auto-generated constructor stub
		this.snackName=snackName;
		this.snackPrice=snackPrice;
		this.snackCount=snackCount;
	}


	public String getSnackName() {
		return snackName;
	}


	public void setSnackName(String snackName) {
		this.snackName = snackName;
	}


	public double getSnackPrice() {
		return snackPrice;
	}


	public void setSnackPrice(double snackPrice) {
		this.snackPrice = snackPrice;
	}


	public int getSnackCount() {
		return snackCount;
	}


	public void setSnackCount(int snackCount) {
		this.snackCount = snackCount;
	}




}
