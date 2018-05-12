package strategy;

import java.util.ArrayList;
import java.util.List;

import logic.GridManager;
import logic.RandomNumber;

public class GreaterThanStrategy extends Mode {

	private List<GridManager> list;
	private RandomNumber random;
	private int size;
	private int base;

	public GreaterThanStrategy() {

		random = RandomNumber.getInstance();
		this.base = 80;
		size = random.getSize();
		list = new ArrayList<>();		
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
		
		}


	@Override
	public List<GridManager> getPuzzle() {
		return list;
	}

	@Override
	public int getBase() {
		return base;
	}

	@Override
	public void clearPuzzle() {
		list.clear();
		
	}

}
