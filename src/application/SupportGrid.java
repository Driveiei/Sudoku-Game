package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.BoxManager;
import strategy.Mode;

public class SupportGrid {
	
	private Mode mode;
	private Random rand;
	
	private int size;
	private int realSize;
	
	public SupportGrid(int size,Mode mode) {
		rand = new Random();
		this.size = size;
		realSize = size*size;
		this.mode = mode;
	}
	
	public int randomCursor(int size) {
		return rand.nextInt(size);
	}

	public int adaptGrid(int innerLoop, int outerLoop) {
		return (outerLoop / size) * size + innerLoop / size;
	}

	public int adaptBox(int innerLoop, int outerLoop) {
		return size * (outerLoop % size) + innerLoop % size;
	}

	public int scaleToArrayColumn(int grid, int box) {
		return (grid % size) * size + box % size;
	}

	public int scaleToArrayRow(int grid, int box) {
		return (grid / size) * size + box / size;
	}
	
	public List<BoxManager> getSpaceBoxList() {
		List<BoxManager> puzzle = new ArrayList<BoxManager>();
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = adaptGrid(selectBox, selectGrid);
				int box = adaptBox(selectBox, selectGrid);
				BoxManager oneBox = mode.getPuzzle().get(grid).getList().get(box);
				if (!oneBox.getLock()) {
					puzzle.add(oneBox);
				}
			}
		}
		return puzzle;
	}
}
