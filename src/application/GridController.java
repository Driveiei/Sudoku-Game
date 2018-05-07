package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import logic.BoxManager;
import logic.GridManager;
import strategy.Mode;

public class GridController {


	@FXML
	BorderPane borderPane;

	//Declare class
	private Grid grid;
	private Mode mode;
	
	//funny attribute
	private int size;
	private int realSize;
	private boolean finish;
	
	@FXML
	public void initialize() {
		Mode.setMode("easy");
		mode = Mode.getInstance();
		mode.setPuzzle();
		
		size = mode.getSize();
		realSize = size*size;
		finish = false;
		
//		//setMode
//		String x = "easy";
//		Mode.setFactory(x, table);
//		mode = Mode.getInstance(table);
//		mode.setPuzzle();
//		mode.randomInvisible();
		
		grid = new Grid(borderPane,mode);
		
	}
	
	public void handleClear(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for(int selectGrid = 0; selectGrid<realSize; selectGrid++) {
			for(int selectBox = 0; selectBox < realSize; selectBox++) {
				BoxManager box = puzzle.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid));
				if(!box.getLock()) {
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
				BoxManager box = puzzle.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid));
				if(!box.getLock()) {
					String text = Integer.toString(box.getNumber());
					if(grid.getLabel()[selectBox][selectGrid].getText().equals(text)) {
						
					}
				}
			}
		}
	}
	
	public int adaptGrid(int column,int row) {
		return (row/size)*size+column/size;
	}
	
	public int adaptBox(int column,int row) {
		return size*(row%size)+column%size;
	}
	
//	public int callListNumber(List<GridManager> list ,int grid,int column) {
//		return list.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid)).getNumber();
//	}
	

}