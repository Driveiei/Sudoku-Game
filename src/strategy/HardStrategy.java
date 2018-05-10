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
	private int base;
	private int realSize;
	private final int PERCENTAGE = 10;
	private final int ONE_HUNDRED = 100;

	public HardStrategy() {
//		this.table = table; // 3 or 4
//		random = new RandomNumber(table);
//		random.run(); // list
		random = RandomNumber.getInstance();
		if(random.getSize() == 3) 
			this.base = 80;
		else this.base = 45;
		size = random.getSize();
		realSize = size*size;
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
		for (int grid = 0; grid < realSize; grid++) {
			for (int box = 0; box < realSize; box++) {
				int percentage = rand.nextInt(ONE_HUNDRED);
				if (percentage <= PERCENTAGE) {
					list.get(grid).getList().get(box).setLock(true);
					list.get(grid).getList().get(box).setCheck(true);
				}else { 
					list.get(grid).getList().get(box).setLock(false);
					list.get(grid).getList().get(box).setCheck(false);
				}
			}
		}
	}

	@Override
	public List<GridManager> getPuzzle() {
		return list;
	}

	@Override
	public int getBase() {
		return base;
	}

}
