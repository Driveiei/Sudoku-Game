package logic;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<GridManager> sudoku;
	private int size;
	private int code = 1;

	public Table(int size) {
		this.size = size;
		sudoku = new ArrayList<GridManager>();
	}

	public List<Integer> duplicateRow(int numberGrid, int row) {
		int position = numberGrid % size;
		int rowGrid = row / size;
		int rowBox = row % size;
		List<Integer> check = new ArrayList<Integer>();
		if (position == 0)
			return check;// ???
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(size * rowGrid + times).getList().get(size * rowBox + box).getNumber());
			}
		}
		return check;
	}

	public List<Integer> duplicateColumn(int numberGrid, int column) {
		int position = numberGrid / size;
		int columnGrid = column / size;
		int columnBox = column % size;
		List<Integer> check = new ArrayList<Integer>();
		if (position == 0)
			return check;// ???
		for (int times = 0; times < position; times++) {
			for (int box = 0; box <= (size - 1); box++) {
				check.add(sudoku.get(columnGrid + (size * times)).getList().get(size * box + columnBox).getNumber());
			}
		}
		return check;
	}

	public void insert(int numberGrid, int target) {
		sudoku.get(numberGrid).getList().add(new BoxManager(target, false, false,code++));
	}
	
	public void clear(int numberGrid) {
		sudoku.get(numberGrid).getList().clear();
	}

	public int getSize() {
		return size;
	}

	public List<GridManager> getList() {
		return sudoku;
	}
	
	public int getCode() {
		return code;
	}
}
