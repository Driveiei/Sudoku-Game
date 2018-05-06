package strategy;

import java.util.List;

import logic.GridManager;
import logic.Table;

public abstract class ModeFactory {
	
	private static ModeFactory factory = null;
	
//	public abstract Table createSudoku(List);
		
	public abstract void randomInvisible();
	
	public static ModeFactory getInstance(Table table) {
		if (factory == null)
			factory = new EasyStrategy(table);
		return factory;
	}
	
	public static void setFactory(String text,Table table) {
		if(text.equals("easy"))
		factory = new EasyStrategy(table);
		else factory = new HardStrategy(table);
	}
	
	public abstract void setPuzzle() ;
	
	public abstract List<GridManager> getPuzzle() ;
	
	}
