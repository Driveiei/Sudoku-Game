package strategy;

import java.util.List;

import logic.GridManager;

public abstract class Mode {
	
	private static Mode mode = null;
		
	public abstract void randomInvisible();
	
	public static Mode getInstance() {
		if (mode == null)
			mode = new EasyStrategy();
		return mode;
	}
	
	public static void setMode(Mode strategy) {
		mode = strategy;
	}
	
	public abstract int getSize(); 
	
	public abstract void setPuzzle() ;
	
	public abstract List<GridManager> getPuzzle() ;
	
	public abstract int getBase();
	
	public abstract void clearPuzzle();
	
	
	
	}
