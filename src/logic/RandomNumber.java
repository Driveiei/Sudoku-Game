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
		int size = (table.getSize() * table.getSize()) - 1; // 8
		for (int i = 0; i <= size; i++) {
			table.getList().add(new GridManager(i, true));// create 9 grids.
		}
		// List<Integer> setOfNumber = createNumberSet(size+1);

		// for (int i = 0; i <= size; i++) {
		// table.getList().get(i).getList().add(new BoxManager(i, true, true));
		// }
		for (int i = 0; i <= size; i++) {
			System.out.println("Number Grid : " + i);
			createRandomSet(createNumberSet(size + 1), i); // create grid 0 1 2 3 4 5 6 7 8
			System.out.println("show Grid ");
			for (int y = 0; y <= size; y++) {
				System.out.println(table.getList().get(i).getList().get(y).getNumber());
			}
		}
		
		
		
		System.out.println("Fuck========================================ADsaDA");
		for(int i = 0; i<= 8; i++) {
			System.out.println("Block "+i);
			for(int y = 0; y<=8;y++) {
				System.out.println(table.getList().get(i).getList().get(y).getNumber());
			}
		}
		
		// for(int maxSize = size+1; maxSize>0; maxSize--) {
		// int cursor = rand.nextInt(maxSize);
		// System.out.println("position : "+cursor);
		// System.out.println("value : "+ setOfNumber.get(cursor));
		// setOfNumber.remove(cursor);
		// }

	}

//	public void createRandomSet(List<Integer> setOfNumber, int numberGrid) {
//		int size = setOfNumber.size();// 9
//		int cursor = 0;
//		List<Integer> collecting = new ArrayList<Integer>();
//		int count = 0;
//		int col;
//		int ro;
//		int times = 0;
//		// 9//8//7//6//5-->//0
//		int target;
//		for (int maxSize = size; maxSize > 0; maxSize--) {
//			col = (numberGrid % 3) * 3 + count % 3;// 0//1//2//0//1//2//0//1//2
//			ro = (numberGrid / 3) * 3 + count / 3;// 0//0//0//1//1//1//2//2//2
//			count++;
//			while (true) {
//				cursor = rand.nextInt(maxSize); // random 0-8 position
//				System.out.println("cursor is" + cursor);
//				target = setOfNumber.get(cursor);
//				// cursor--;
//				if (numberGrid != 0) {
//					System.out.println("col is " + col);
//					System.out.println("ro is " + ro);
//					System.out.println("Set of Number is");
//					for (int x : setOfNumber) {
//						System.out.println(x);
//					}
//					System.out.println("target is " + target);
//					System.out.println("==============");
//				}
//
//				if (table.duplicateColumn(numberGrid, col, target) && table.duplicateRow(numberGrid, ro, target)) {
//					if (numberGrid != 0) {
//						System.out.println("OK");
//						System.out.println("column is " + table.duplicateColumn(numberGrid, col, target));
//						System.out.println("Row is " + table.duplicateRow(numberGrid, ro, target));
//					}
//					table.getList().get(numberGrid).getList().add(new BoxManager(target, true, true));
//					// System.out.println("value : "+ setOfNumber.get(cursor));
//					if (collecting.size() != 0) {
//						setOfNumber.addAll(collecting);
//						collecting.clear();
//					}
//					setOfNumber.remove(cursor);
//					cursor = cursor + times;
//					times = 0;
//					break;
//				} else {
//					if (numberGrid != 0) {
//						System.out.println("NO OK");
//						System.out.println("column is " + table.duplicateColumn(numberGrid, col, target));
//						System.out.println("Row is " + table.duplicateRow(numberGrid, ro, target));
//					}
//					collecting.add(target);
//					setOfNumber.remove(cursor);
//					times++;
//					cursor--;
//				}
//			}
//
//		}
//	}

//	public void createRandomSet(List<Integer> setOfNumber, int numberGrid) {
//		int row = 0;
//		int count = 0;
//		int column = 0;
//		int size = setOfNumber.size();// 9
//		int cursor;
//		int target;
//		List<Integer> remover = new ArrayList<Integer>();
//		
//		while(count <3) {
//			List<Integer> collecting = new ArrayList<Integer>();
//			collecting.addAll(table.duplicateColumn(numberGrid,(numberGrid%3)*3+count));
//			List<Integer> fun = new ArrayList<Integer>();
//			fun.addAll(table.duplicateRow(numberGrid,(numberGrid/3)*3+count));
//			
//			List<Integer> total = new ArrayList<Integer>();
//			for(Integer y : collecting) {
//				if(!total.contains(y)) {
//				total.add(y);
//				}
//			}
//			for(Integer y : fun) {
//				if(!total.contains(y)) {
//				total.add(y);
//				}
//			}
//			
//			if(numberGrid == 1) {
//				System.out.println("-------");
//				System.out.println("===row====");
//				for(Integer o : fun) {
//					System.out.println(o);
//				}
//				System.out.println("===column===");
//				for(Integer o : collecting) {
//					System.out.println(o);
//				}
//				System.out.println("--------");
//			}
//			
////			int help;
////			for(Integer x : collecting) {
////				try{
////					help = setOfNumber.indexOf(x);
////					setOfNumber.remove(help);	
////				}catch(IndexOutOfBoundsException ex) {
////					
////				}
////			}
////			for(Integer y : fun) {
////				try{
////					help = setOfNumber.indexOf(y);
////					setOfNumber.remove(help);	
////				}catch(IndexOutOfBoundsException ex) {
////					
////				}
////			}
//			int help;
//			for(Integer y : total) {
//				help = setOfNumber.indexOf(y);
//				setOfNumber.remove(help);
//			}
//			
//			System.out.println("all number is : ");
//			for(Integer x : setOfNumber) {
//				System.out.println(x);
//			}
//			
//			for (int maxSize = setOfNumber.size(); maxSize > 6; maxSize--) {
//				cursor = rand.nextInt(maxSize); // random 0-8 position
////				System.out.println("cursor is " + cursor);
//				target = setOfNumber.get(cursor);
////				System.out.println("target is " + target);
////				System.out.println("=========");
//				table.getList().get(numberGrid).getList().add(new BoxManager(target, true, true));
//				remover.add(target);
//				int j = setOfNumber.indexOf(target);
//				setOfNumber.remove(j);
//			}
//			System.out.println("Show what u add");
//			for(Integer g : remover) {
//				System.out.println(g);
//			}
////			setOfNumber.addAll(remover);
//			setOfNumber.addAll(total);
//			total.clear();
//			remover.clear();
//			collecting.clear();
//			fun.clear();
//			count++;
//		}
//	}
	
	
	//Good
//	public void createRandomSet(List<Integer> setOfNumber, int numberGrid) {
//		int size = table.getSize()*table.getSize()-1;//8
//		int realSize = table.getSize();//3
//		int help1 = numberGrid%3;
//		int help2 = numberGrid/3;
//		List<Integer> collecting = new ArrayList<Integer>();
//		List<Integer> forDeleteColumn = new ArrayList<Integer>();
//		List<Integer> forDeleteRow = new ArrayList<Integer>();
//		int countOut = 0;
//		for(int i = 0; i <= size; i++) {
////			column = run % 3;
////			row = run / 3;
//			forDeleteColumn.addAll(table.duplicateColumn(numberGrid, realSize*help1+i%3));
//			forDeleteRow.addAll(table.duplicateRow(numberGrid, realSize*help2+i/3));
////			run++;
//			for(Integer out : forDeleteColumn) {
//				if(setOfNumber.contains(out)) {
//					setOfNumber.remove(setOfNumber.indexOf(out));
//					collecting.add(out);
//					countOut++;
//				}
//			}
//			for(Integer out : forDeleteRow) {
//				if(setOfNumber.contains(out)) {
//					setOfNumber.remove(setOfNumber.indexOf(out));
//					collecting.add(out);
//					countOut++;
//				}
//			}
//			
////			System.out.print("ForDeleteColumn");
////			for(Integer x : forDeleteColumn) {
////				System.out.print(x+" ");
////			}
////			System.out.println();
////			System.out.println("ForDeleteRow");
////			for(Integer x : forDeleteRow) {
////				System.out.print(x+" ");
////				System.out.println();
////			}
////			System.out.println();
//
//			
//			int number = setOfNumber.size();
//			System.out.println("number is" + number);
//			int cursor = rand.nextInt(number); // random 0-8 position
////									if(i==0) {
//											System.out.println("cursor is : "+cursor);
//											System.out.println("Set of Number is ");
//											for(Integer y : setOfNumber) {
//												System.out.println(y);
//											}
////									}
//			int target = setOfNumber.get(cursor);
//			table.getList().get(numberGrid).getList().add(new BoxManager(target,true,true));
//			
//			setOfNumber.remove(setOfNumber.indexOf(target));
//			setOfNumber.addAll(collecting);
//			collecting.clear();
//			forDeleteColumn.clear();
//			forDeleteRow.clear();
//			countOut = 0;
//		}
//		
//	}
	
	public void createRandomSet(List<Integer> setOfNumber, int numberGrid) {
		int size = table.getSize()*table.getSize()-1;//8
		int realSize = table.getSize();//3
		int help1 = numberGrid%3;
		int help2 = numberGrid/3;
		List<Integer> collecting = new ArrayList<Integer>();
		List<Integer> forDeleteColumn = new ArrayList<Integer>();
		List<Integer> forDeleteRow = new ArrayList<Integer>();
		
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(setOfNumber);		
		for(int i = 0; i <= size; i++) {
			forDeleteColumn.addAll(table.duplicateColumn(numberGrid, realSize*help1+i%3));
			forDeleteRow.addAll(table.duplicateRow(numberGrid, realSize*help2+i/3));
			
			for(Integer out : forDeleteColumn) {
				if(setOfNumber.contains(out)) {
					setOfNumber.remove(setOfNumber.indexOf(out));
					collecting.add(out);
				}
			}
			for(Integer out : forDeleteRow) {
				if(setOfNumber.contains(out)) {
					setOfNumber.remove(setOfNumber.indexOf(out));
					collecting.add(out);
				}
			}
			try {
				int number = setOfNumber.size();			
				int cursor = rand.nextInt(number); // random 0-8 position
				int target = setOfNumber.get(cursor);
				table.getList().get(numberGrid).getList().add(new BoxManager(target,true,true));
				setOfNumber.remove(setOfNumber.indexOf(target));
				setOfNumber.addAll(collecting);
			} catch (IllegalArgumentException ex) {
				List<BoxManager> r = new ArrayList<BoxManager>();
				r.addAll(table.getList().get(numberGrid).getList());
				for(BoxManager t : r) {
					System.out.println(t.getNumber());
				}
				i = -1;				
				setOfNumber.clear();
				setOfNumber.addAll(copy);
				table.getList().get(numberGrid).getList().clear();
				if(numberGrid %3 == 1) {
					table.getList().get(numberGrid-1).getList().clear();
					createRandomSet(createNumberSet(9), numberGrid-1); // create grid 0 1 2 3 4 5 6 7 8
				}
				if(numberGrid %3 == 2) {
					table.getList().get(numberGrid-2).getList().clear();
					table.getList().get(numberGrid-1).getList().clear();
					createRandomSet(createNumberSet(9), numberGrid-2); 
					createRandomSet(createNumberSet(9), numberGrid-1); 
				}
			}
			collecting.clear();
			forDeleteColumn.clear();
			forDeleteRow.clear();
		}
		
	}
	
//Drive	
//	public void createRandomSet(List<Integer> setOfNumber, int numberGrid) {
//		int size = table.getSize()*table.getSize()-1;//8
//		int realSize = table.getSize();//3
//		int help1 = numberGrid%3;
//		int help2 = numberGrid/3;
//		List<Integer> collecting = new ArrayList<Integer>();
//		List<Integer> forDeleteColumn = new ArrayList<Integer>();
//		List<Integer> forDeleteRow = new ArrayList<Integer>();
//		int countOut = 0;
//		for(int i = 0; i <= size; i++) {
////			column = run % 3;
////			row = run / 3;
//			forDeleteColumn.addAll(table.duplicateColumn(numberGrid, realSize*help1+i%3));
//			forDeleteRow.addAll(table.duplicateRow(numberGrid, realSize*help2+i/3));
//			//			run++;
//			for(Integer out : forDeleteColumn) {
//				if(setOfNumber.contains(out)) {
//					setOfNumber.remove(setOfNumber.indexOf(out));
//					collecting.add(out);
//					countOut++;
//				}
//			}
//			for(Integer out : forDeleteRow) {
//				if(setOfNumber.contains(out)) {
//					setOfNumber.remove(setOfNumber.indexOf(out));
//					collecting.add(out);
//					countOut++;
//				}
//			}
//			
//			System.out.print("ForDeleteColumn");
//			for(Integer x : forDeleteColumn) {
//				System.out.print(x+" ");
//			}
//			System.out.println();
//			System.out.println("ForDeleteRow");
//			for(Integer x : forDeleteRow) {
//				System.out.print(x+" ");
//				System.out.println();
//			}
//			System.out.println();
//
//			
//			int number = setOfNumber.size();
//			int cursor = 0;
//			int target = 0;
//			try {
//			cursor = rand.nextInt(number); // random 0-8 position
//			target = setOfNumber.get(cursor);
//			table.getList().get(numberGrid).getList().add(new BoxManager(target,true,true));
//			setOfNumber.remove(setOfNumber.indexOf(target));
//			setOfNumber.addAll(collecting);
//			} catch(IllegalArgumentException ex) {
//				table.getList().get(numberGrid).getList().clear();
//				table.getList().get(numberGrid-1).getList().clear();
//				i = i-2;
//			}
//			//									if(i==0) {
//											System.out.println("cursor is : "+cursor);
//											System.out.println("Set of Number is ");
//											for(Integer y : setOfNumber) {
//												System.out.println(y);
//											}
//
//			//									}
//			collecting.clear();
//			forDeleteColumn.clear();
//			forDeleteRow.clear();
//			countOut = 0;
//		}
//		
//	}
	

	public List<Integer> createNumberSet(int size) {
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			number.add(i);
		}
		return number;
	}
}
