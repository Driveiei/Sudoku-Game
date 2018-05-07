package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.GridManager;
import logic.RandomNumber;

public class HardStrategy extends Mode {

	private List<GridManager> list;
	private RandomNumber random;
	private Random rand;
	private int size;


	public HardStrategy() {
//		this.table = table; // 3 or 4
//		random = new RandomNumber(table);
//		random.run(); // list
		random = RandomNumber.getInstance();
		size = random.getSize();//3
		list = new ArrayList<>();
		rand = new Random();
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public void setPuzzle() {
		list.addAll(random.getPuzzle());
		randomInvisible();
	}

	@Override
	public void randomInvisible() {
		for (int grid = 0; grid < 9; grid++) {
			for (int box = 0; box < 9; box++) {
				int percentage = rand.nextInt(100);
				if (percentage <= 30) {
					list.get(grid).getList().get(box).setLock(true);
				} else {
					list.get(grid).getList().get(box).setLock(false);
				}
			}
		}
	}

	@Override
	public List<GridManager> getPuzzle() {
		return list;
	}

}