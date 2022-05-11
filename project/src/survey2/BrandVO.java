package survey2;

public class BrandVO {
	private int number;
	private String brand;
	private int count;
	
	public BrandVO() {}
	
	public BrandVO(int number, String brand, int count) {
		super();
		this.number = number;
		this.brand = brand;
		this.count = count;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return number+". "+brand + " " + count + "ǥ\n";
	}
	

}
