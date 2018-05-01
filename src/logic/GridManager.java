package logic;

import java.util.ArrayList;
import java.util.List;

public class GridManager {
	private int numGrid;
	private boolean checkGrid;
	private List<BoxManager> list;
	
	public GridManager(int numGrid,boolean checkGrid) {
		this.numGrid = numGrid;
		this.checkGrid = checkGrid;
		list = new ArrayList<BoxManager>();
	}
	
	public int getPoint() {
		return numGrid;
	}
	
	public boolean getCheckGrid() {
		return checkGrid;
	}
	
	public List<BoxManager> getList() {
		return list;
	}
}
