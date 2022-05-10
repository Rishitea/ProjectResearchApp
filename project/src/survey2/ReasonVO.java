package survey2;

public class ReasonVO {
	private int number;
	private String reason;
	private int count;
	private int coffee_number;
	
	public ReasonVO() {}

	public ReasonVO(int number, String reason, int count, int coffee_number) {
		super();
		this.number = number;
		this.reason = reason;
		this.count = count;
		this.coffee_number = coffee_number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCoffee_number() {
		return coffee_number;
	}

	public void setCoffee_number(int coffee_number) {
		this.coffee_number = coffee_number;
	}

	@Override
	public String toString() {
		return "ReasonVO [number=" + number + ", reason=" + reason + ", count=" + count + ", coffee_number="
				+ coffee_number + "]";
	}
	

}
