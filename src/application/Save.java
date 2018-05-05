package application;

public class Save {
	private static int num;
	
	public Save(int num) {
		Save.num = num;
	}
	
	public static int getNum() {
		return num;
	}
}
