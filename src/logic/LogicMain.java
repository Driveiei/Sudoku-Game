package logic;

import java.util.ArrayList;
import java.util.List;

public class LogicMain {

	public static void main(String[] args) {
		Table table = new Table(3); // 3 or 4
		RandomNumber random = new RandomNumber(table);
		random.run();
	}

}
