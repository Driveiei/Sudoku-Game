package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import logic.BoxManager;
import logic.GridManager;
import strategy.Mode;

public class GridController {

	@FXML
	BorderPane borderPane;

	@FXML
	Label timer;

	private TimeTask worker;

	// Declare class
	private Grid griddy;
	private Mode mode;
	private Random rand;

	// funny attribute
	private int size;
	private int realSize;
	private boolean finish;

	@FXML
	public void initialize() {
		runTime();

		mode = Mode.getInstance();
		mode.setPuzzle();
		rand = new Random();

		size = mode.getSize();
		realSize = size * size;
		finish = false;

		griddy = new Grid(borderPane, mode);

	}

	public void handleClear(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				BoxManager box = puzzle.get(adaptGrid(selectBox, selectGrid)).getList()
						.get(adaptBox(selectBox, selectGrid));
				if (!box.getLock()) {
					griddy.getLabel()[selectBox][selectGrid].setText("");
				}
			}
		}
	}

	public void handleHint(ActionEvent event) {
		System.out.println("xxxx");
		List<BoxManager> puzzle = new ArrayList<BoxManager>();
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = adaptGrid(selectBox, selectGrid);
				int box = adaptBox(selectBox, selectGrid);
				if (!mode.getPuzzle().get(grid).getList().get(box).getLock()) {
					puzzle.add(mode.getPuzzle().get(grid).getList().get(box));					
				}
			}
		}
		System.out.println("puzzle size : " + puzzle.size());
		if(puzzle.size() == 0) return;
		int cursor = randomCursor(puzzle.size());
		System.out.println("cursor is "+cursor);
		BoxManager targetBox = puzzle.get(cursor);
		String target = Integer.toString(targetBox.getNumber());
		int numberOfGrid = -1;
		int numberOfBox = 0;
		while(++numberOfGrid < realSize) {
			numberOfBox = mode.getPuzzle().get(numberOfGrid).getList().indexOf(targetBox);
			if(mode.getPuzzle().get(numberOfGrid).getList().indexOf(targetBox) >= 0) {
				
				break;
			}
		}
		int column = scaleToArrayColumn(numberOfGrid,numberOfBox);
		int row = scaleToArrayRow(numberOfGrid,numberOfBox);
		System.out.println("Column : "+column);
		System.out.println("Row : " +row);
		System.out.println(target);
		griddy.getLabel()[column][row].setText(target);
		int a = adaptGrid(column, row);
		int b = adaptBox(column, row);
		mode.getPuzzle().get(a).getList().get(b).setLock(true);
		griddy.setMouses(column,row);
	}
	
	public int scaleToArrayColumn(int a, int b) {
		return (a%size)*size + b%size;
	}
	
	public int scaleToArrayRow(int a, int b) {
		return (a/size)*size+b/size;
	}
	
	
	public int randomCursor(int size) {
		return rand.nextInt(size);
	}

	public void handleDone(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		List<String> userInput = new ArrayList<String>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				if (!puzzle.get(adaptGrid(selectBox, selectGrid)).getList().get(adaptBox(selectBox, selectGrid))
						.getLock()) {

				}
			}
		}
	}

	public void handleDones(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		List<String> saveInvisible = new ArrayList<String>();
		List<String> saveUserInput = new ArrayList<String>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				if (!puzzle.get((selectGrid / size) * size + selectBox / size).getList()
						.get(size * (selectGrid % size) + selectBox % size).getLock()) {
					String x = Integer.toString(puzzle.get((selectGrid / size) * size + selectBox / size).getList()
							.get(size * (selectGrid % size) + selectBox % size).getNumber());
					saveInvisible.add(x);
//					System.out.println("Invisible number = "+x);
//					System.out.println("UserInput number = "+ );
					if (griddy.getLabel()[selectBox][selectGrid].getText().equals(x)) {
						if (!griddy.getLabel()[selectBox][selectGrid].getText().equals("")) {
							System.out.println("Invisible number = "+x);
							System.out.println("UserInput number = "+griddy.getLabel()[selectBox][selectGrid].getText() );
							saveUserInput.add(griddy.getLabel()[selectBox][selectGrid].getText());
						} else {
							return;
						}

					}
				}
			}
		}
		System.out.println(saveInvisible.size());
		System.out.println(saveUserInput.size());
		if (saveInvisible.size() == saveUserInput.size()) {
			System.out.println("DONE");
		}
	}

	public int adaptGrid(int selectBox, int selectGrid) {
		return (selectGrid / size) * size + selectBox / size;
	}

	public int adaptBox(int selectBox, int selectGrid) {
		return size * (selectGrid % size) + selectBox % size;
	}

	public void runTime() {
		worker = new TimeTask();
		timer.setText("00 : 00 : 000");
		timer.textProperty().bind(worker.messageProperty());
		new Thread(worker).start();

	}
}