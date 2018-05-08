package logic;

import java.util.ArrayList;
import java.util.List;

public class GridManager {
	private int numGrid;
	private List<BoxManager> list;
	
	public GridManager(int numGrid) {
		this.numGrid = numGrid;
		list = new ArrayList<BoxManager>();
	}
	
	public int getPoint() {
		return numGrid;
	}
	
	public List<BoxManager> getList() {
		return list;
	}
}
