package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber{
	
	private static RandomNumber random = null;
	
	private static Table table;
	
	private Random rand;
	
	private RandomNumber() {
		rand = new Random();
	}
	
	public static RandomNumber getInstance() {
		if (random == null) {
			table = new Table(3);
			random = new RandomNumber();
		}
		return random;
	}
	
	public static void setRandomNumber(int number) {
		table = new Table(number);
		random = new RandomNumber();
	}
	
	public int getSize() {
		return table.getSize();
	}
	
	public List<GridManager> getPuzzle() {
		int size = (table.getSize() * table.getSize()); // 9
		for (int grid = 0; grid < size; grid++) {
			table.getList().add(new GridManager(grid));// create 9 grids.
			createRandomSet(createNumberSet(size), grid, size); // create grid 0 1 2 3 4 5 6 7 8
		}
		print(table.getSize());
		return table.getList();
	}
	
	private void print(int number) {
		for(int i = 0; i< number; i++) {
			for(int j = 0; j < number; j++) {
				for(int k =0; k<number; k++) {
					for(int l = 0; l< number;l++) {
						System.out.printf("%2s",table.getList().get(number*i+k).getList().get(number*j+l).getNumber());
						System.out.print(" ");
					}
					System.out.print("| ");
				}
				System.out.println();
			}
			if(number == 4) System.out.print("----------------------------------");
			System.out.println("---------------------");
		}
	}

	private List<Integer> mergeDuplicateList(int numberGrid,int column,int row){
		List<Integer> merge = new ArrayList<Integer>();
		merge.addAll(table.duplicateColumn(numberGrid, column));
		List<Integer> secondList = new ArrayList<Integer>();
		secondList.addAll(table.duplicateRow(numberGrid, row));
		for(Integer number : secondList) {
			if (!merge.contains(number))
				merge.add(number);
		}
		return merge;
	}
	
	private void createRandomSet(List<Integer> setOfNumber, int numberGrid, int size) {
		int realSize = table.getSize();// 3
		int column;
		int row;
		List<Integer> duplicateSet = new ArrayList<Integer>();
		List<Integer> collecting = new ArrayList<Integer>();
		for (int box = 0; box < size; box++) {

			column = identifyColumn(realSize,numberGrid,box);
			row = identifyRow(realSize,numberGrid,box);
			duplicateSet.addAll(mergeDuplicateList(numberGrid,column,row));
			
			for (Integer out : duplicateSet) {
				if (setOfNumber.contains(out)) {
					setOfNumber.remove(setOfNumber.indexOf(out));
					collecting.add(out);
				}
			}
			
			try {
				int target = randomNumber(setOfNumber);
				table.insert(numberGrid, target);
				setOfNumber.remove(setOfNumber.indexOf(target));
				setOfNumber.addAll(collecting);
			} catch (IllegalArgumentException ex) {
				box = undoCreateGrid(numberGrid, size, realSize);
				table.clear(numberGrid);
				setOfNumber.clear();
				setOfNumber.addAll(createNumberSet(size));
			}
			collecting.clear();
			duplicateSet.clear();
		}
	}

	private int identifyRow(int realSize,int numberGrid,int box) {
		return realSize * (numberGrid / realSize) + box / realSize;
	}
	
	private int identifyColumn(int realSize,int numberGrid,int box) {
		return realSize * (numberGrid % realSize) + box % realSize;
	}
	
	private int randomNumber(List<Integer> setOfNumber) {
		int cursor = rand.nextInt(setOfNumber.size());
		int target = setOfNumber.get(cursor);
		return target;
	}
	
	private int undoCreateGrid(int numberGrid, int size, int realSize) {
		int remainder = numberGrid % realSize;
		for(int i = 0 ;i < remainder ;i++) {
			table.clear(numberGrid - remainder + i);
			createRandomSet(createNumberSet(size),numberGrid-remainder+i,size);
		}
		return -1;
	}

	private List<Integer> createNumberSet(int size) {
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			number.add(i);
		}
		return number;
	}
}
