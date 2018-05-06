package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.GridManager;
import logic.RandomNumber;
import logic.Table;

public class HardStrategy extends ModeFactory {

	private List<GridManager> list;
	private Table table; // <<--- list
	private Random rand;

	public HardStrategy(Table table) {
		this.table = table; // 3 or 4
//		random = new RandomNumber(table);
//		random.run(); // list
		list = new ArrayList<>();
		rand = new Random();
	}

	@Override
	public void setPuzzle() {
		list.addAll(table.getList());
	}

	@Override
	public void randomInvisible() {
		for (int j = 0; j < Math.sqrt(list.size()); j++) {
			for (int i = 0; i < Math.sqrt(list.size()); i++) {
				int x = rand.nextInt(100);
				if (x <= 30) {
					list.get(j).getList().get(i).setLock(true);
				} else {
					list.get(j).getList().get(i).setLock(false);
				}
			}
		}
	}

	@Override
	public List<GridManager> getPuzzle() {
		return list;
	}

}