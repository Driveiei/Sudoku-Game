package logic;

public class LogicMain {

	public static void main(String[] args) {
		Table table = new Table(4); // 3 or 4
		RandomNumber random = new RandomNumber(table);
		random.run();
	}

}
