package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import logic.BoxManager;
import logic.GridManager;
import logic.RandomNumber;
import logic.Table;
import strategy.ModeFactory;

public class GridController {


	@FXML
	BorderPane borderPane;

	//Declare class
	private Grid grid;
	private ModeFactory mode;
	private Table table;
	private RandomNumber random;
	
	//funny attribute
	private int size;
	private int realSize;
	private boolean finish;
	
	@FXML
	public void initialize() {
		//setTable
		table = new Table(3);
		random = new RandomNumber(table);
		random.run();
		
		size = table.getSize();
		realSize = size*size;
		finish = false;
		
		//setMode
		String x = "easy";
		ModeFactory.setFactory(x, table);
		mode = ModeFactory.getInstance(table);
		mode.setPuzzle();
		mode.randomInvisible();
		
		grid = new Grid(borderPane,table,mode);
		
	}
	
	public void handleClear(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for(int selectGrid = 0; selectGrid<realSize; selectGrid++) {
			for(int selectBox = 0; selectBox < realSize; selectBox++) {
				if(!puzzle.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid)).getLock()) {
					grid.getLabel()[selectBox][selectGrid].setText("");
				}
			}
		}
	}
	
	public void handleDone(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for(int selectGrid = 0; selectGrid<realSize; selectGrid++) {
			for(int selectBox = 0; selectBox < realSize; selectBox++) {
				if(!puzzle.get((selectGrid/3)*3+selectBox/3).getList().get(3*(selectGrid%3)+selectBox%3).getLock()) {
					String x = Integer.toString(puzzle.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid)).getNumber());
					if(grid.getLabel()[selectBox][selectGrid].getText().equals(x)) {
						
					}
				}
			}
		}
	}
	
	public int adaptGrid(int column,int row) {
		return (row/3)*3+column/3;
	}
	
	public int adaptBox(int column,int row) {
		return 3*(row%3)+column%3;
	}
	
	public int callListNumber(List<GridManager> list ,int grid,int column) {
		return list.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid)).getNumber();
	}
	

}