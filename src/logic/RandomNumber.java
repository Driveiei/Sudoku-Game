package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomNumber implements Runnable {

	private Table table;
	private Random rand;

	public RandomNumber(Table table) {
		this.table = table;
		rand = new Random();
	}

	@Override
	public void run() {
		int size = (table.getSize() * table.getSize()) ; //9
		for (int grid = 0; grid < size; grid++) {
			table.getList().add(new GridManager(grid, true));// create 9 grids.
			createRandomSet(createNumberSet(size), grid,size); // create grid 0 1 2 3 4 5 6 7 8
		}
		
		for(int i = 0; i< size; i++) {
			System.out.println("Block "+i);
			for(int y = 0; y<size;y++) {
				System.out.println(table.getList().get(i).getList().get(y).getNumber());
			}
		}
	}

	public List<Integer> mergeDuplicateList(List<Integer> listOne, List<Integer> listTwo) {
		List<Integer> merge = new ArrayList<Integer>();
		merge.addAll(listOne);
		for (Integer number : listTwo) {
			if (!merge.contains(number))
				merge.add(number);
		}
		return merge;
	}

	public void createRandomSet(List<Integer> setOfNumber, int numberGrid,int size) {
		int realSize = table.getSize();//3
		int column;
		int row;
		List<Integer> duplicateSet = new ArrayList<Integer>();
		List<Integer> collecting = new ArrayList<Integer>();
		for(int box = 0; box < size; box++) {
			
			column = realSize*(numberGrid%realSize)+box%realSize;
			row = realSize*(numberGrid/realSize)+box/realSize;
			
			duplicateSet.addAll(mergeDuplicateList(table.duplicateColumn(numberGrid, column),table.duplicateRow(numberGrid, row)));
			
			for(Integer out : duplicateSet) {
				if(setOfNumber.contains(out)) {
					setOfNumber.remove(setOfNumber.indexOf(out));
					collecting.add(out);
				}
			}
			
			try {
				int cursor = rand.nextInt(setOfNumber.size()); // random 0-8 position
				int target = setOfNumber.get(cursor);
				table.getList().get(numberGrid).getList().add(new BoxManager(target,true,true));
				setOfNumber.remove(setOfNumber.indexOf(target));
				setOfNumber.addAll(collecting);
			} catch (IllegalArgumentException ex) {
				box = undoCreateGrid(numberGrid,size);				
				setOfNumber.clear();
				setOfNumber.addAll(createNumberSet(size));
				table.getList().get(numberGrid).getList().clear();
			}
			collecting.clear();
			duplicateSet.clear();
		}
	}

	public int undoCreateGrid(int numberGrid,int size) {
		if(numberGrid%3 ==1) {
			table.getList().get(numberGrid-1).getList().clear();
			createRandomSet(createNumberSet(size), numberGrid-1,size);
		}
		if(numberGrid %3 == 2) {
			table.getList().get(numberGrid-2).getList().clear();
			table.getList().get(numberGrid-1).getList().clear();
			createRandomSet(createNumberSet(size), numberGrid-2,size); 
			createRandomSet(createNumberSet(size), numberGrid-1,size); 
		}
		return -1;
	}
	
	public List<Integer> createNumberSet(int size) {
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			number.add(i);
		}
		return number;
	}
}
