package survey2;

public class CoffeeVO {
	private int number;
	private String category;
	private int count;
	
	public CoffeeVO() {}

	public CoffeeVO(int number, String category, int count) {
		super();
		this.number = number;
		this.category = category;
		this.count = count;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CoffeeVO [number=" + number + ", category=" + category + ", count=" + count + "]";
	}
	
}
