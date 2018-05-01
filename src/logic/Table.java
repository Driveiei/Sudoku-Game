package logic;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<GridManager> sudoku;
	private int size;
	public Table(int size, GridManager table) {
		sudoku = new ArrayList<GridManager>();
		this.size = size;
		// size 3 or 4.
	}

	public boolean duplicateGrid(int number) {
		// int x = table.getPoint();
		// int times = capacity/x;
		// int remainder = x%capacity;
		// switch(remainder) {
		// case 1 :
		//
		// }

		// -----------------------
		// List<Griddy> oneGrid = sudoku.get(number).getList();
		// List<Integer> x = new ArrayList<Integer>();
		// for(Griddy grid : oneGrid) {
		// x.add(grid.getNumber());
		// }
		// if(x.size() != oneGrid.size()) {
		// return false;
		// }
		// --------------

		// for(int i = 0;i<capacity; i ++) {
		// oneGrid.g
		// x.add(oneGrid.getNumber());
		// }

		// for(Integer y : oneGrid.getNumber()) {
		//
		// }
		return true;
	}

	public boolean duplicateRow(int numberGrid, int row, int target) {
		// numbergrid 0-8
		// row 0-8
		// target 1-9
		int position = numberGrid % size;
		int rowGrid = row / size;
		int rowBox = row % size;
		List<Integer> check = new ArrayList<Integer>();
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(size * rowGrid + times).getList().get(size * rowBox + box).getNumber());
			}
		}
		if (check.indexOf(target) < 0) {
			return false;
		}
		return true;
	}

	public boolean duplicateColumn(int numberGrid, int column, int target) {
		// numbergrid 0-8
		// column 0-8
		// target 1-9
		int position = numberGrid / size;
		int columnGrid = column / size;
		int columnBox = column % size;
		List<Integer> check = new ArrayList<Integer>();
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(columnGrid + (size * times)).getList().get(size * box + columnBox).getNumber());
			}
		}
		if (check.indexOf(target) < 0) {
			return false;
		}
		return true;
	}

	public boolean add() {
		
		return true;
	}
	
	public boolean insert() {
		// // if the purse is already full then can't insert anything.
		// if (isFull() || value.getValue() <= 0) {
		// return false;
		// } else {
		// money.add(value);
		// return true;
		// }
		return false;
	}
}
