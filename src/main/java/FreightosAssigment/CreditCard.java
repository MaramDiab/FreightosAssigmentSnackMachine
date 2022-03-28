package FreightosAssigment;

import java.util.Objects;


public class CreditCard {
	

	private String cardId;
	private double amount;
	public CreditCard(String cardId, double amount) {
		// TODO Auto-generated constructor stub
		this.amount=amount;
		this.cardId=cardId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	@Override
	public int hashCode() {
	int	result =Objects.hash(cardId);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
	
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}
	public double updateAmount(double value) {
		if(value<=this.amount)
			this.amount=this.amount-value;
		return this.amount;
		
	}
	

}
