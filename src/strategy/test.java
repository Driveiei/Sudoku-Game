package strategy;

import java.util.ArrayList;
import java.util.List;

import logic.GridManager;
import logic.RandomNumber;
import logic.Table;

public class test {

	public static void main(String[] args) {
		List<GridManager> trydo = new ArrayList<GridManager>();
		Table table = new Table(4); // 3 or 4
		RandomNumber random = new RandomNumber(table);
		trydo.addAll(table.getList());
		String x = "easy";
		ModeFactory.setFactory(x);
		ModeFactory mode = ModeFactory.getInstance(); 
		mode.setPuzzle();
		mode.randomInvisible();
	}

}
