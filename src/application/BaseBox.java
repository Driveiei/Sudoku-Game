package application;

public class BaseBox {
	
	private int BASE;
	private int number;
	private String stage;
	
	public BaseBox(int number) {
		this.number = number;
		if(number == 3) BASE = 72;
		else BASE = 40;
	}
	
	public BaseBox(String stage) {
		this.stage = stage;
	}
	
	public int getBase() {
		return BASE;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getStage() {
		return stage;
	}
}
