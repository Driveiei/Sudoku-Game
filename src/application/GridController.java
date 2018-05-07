package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import logic.BoxManager;
import logic.GridManager;
import logic.RandomNumber;
import strategy.Mode;

public class GridController {

	@FXML
	BorderPane borderPane;

	// Declare class
	private Grid grid;
	private Mode mode;
	private BaseBox baseBox;

	// funny attribute
	private int size;
	private int realSize;
	private boolean finish;

	@FXML
	public void initialize() {

		RandomNumber.setRandomNumber(4);
		Mode.setMode("easy");
		mode = Mode.getInstance();
		mode.setPuzzle();

		size = mode.getSize();
		realSize = size * size;
		finish = false;

		grid = new Grid(borderPane, mode);

	}

	public void handleClear(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				BoxManager box = puzzle.get(adaptGrid(selectBox, selectGrid)).getList()
						.get(adaptBox(selectBox, selectGrid));
				if (!box.getLock()) {
					grid.getLabel()[selectBox][selectGrid].setText("");
				}
			}
		}
	}

	public void handleDone(ActionEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		List<String> userInput = new ArrayList<String>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {

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
				if (!puzzle.get((selectGrid / 3) * 3 + selectBox / 3).getList()
						.get(3 * (selectGrid % 3) + selectBox % 3).getLock()) {
					String x = Integer.toString(puzzle.get((selectGrid / 3) * 3 + selectBox / 3).getList()
							.get(3 * (selectGrid % 3) + selectBox % 3).getNumber());
					saveInvisible.add(x);
					if (grid.getLabel()[selectBox][selectGrid].getText().equals(x)) {
						if (!grid.getLabel()[selectBox][selectGrid].getText().equals("")) {
							saveUserInput.add(grid.getLabel()[selectBox][selectGrid].getText());
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

	public int adaptGrid(int column, int row) {
		return (row / size) * size + column / size;
	}

	public int adaptBox(int column, int row) {
		return size * (row % size) + column % size;
	}

	// public int callListNumber(List<GridManager> list ,int grid,int column) {
	// return
	// list.get(adaptGrid(selectBox,selectGrid)).getList().get(adaptBox(selectBox,selectGrid)).getNumber();
	// }

}