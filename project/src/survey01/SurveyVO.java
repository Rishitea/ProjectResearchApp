package survey01;

public class SurveyVO {
	private int number;
	private String name;
	private int count;
	
	public SurveyVO() {}

	public SurveyVO(int number, String name, int count) {
		super();
		this.number = number;
		this.name = name;
		this.count = count;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return number + ". " + name + " ==> " + count + "ǥ\n";
	}

}
