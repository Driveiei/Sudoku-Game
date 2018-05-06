package application;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import logic.RandomNumber;
import logic.Table;
import strategy.ModeFactory;

public class GridController {
	// @FXML
	// BorderPane pane;

	@FXML
	BorderPane borderPane;

	//
	// private Grid grid;
	// private Save save;
	private GridPane mainGrid;
	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;

	private Table table;
	private RandomNumber random;
	private ModeFactory mode;

	private final int BASE = 75;

	@FXML
	public void initialize() {

		table = new Table(3);
		random = new RandomNumber(table);
		random.run();

		mainGrid = new GridPane();
		seperateMainGrid();
		borderPane.setCenter(mainGrid);
		mainGrid.setAlignment(Pos.CENTER);

		subGrid = new GridPane[3][3];// create
		pane = new Pane[9][9];
		label = new Label[9][9];

		createSubGrid();
		modifySubGrid();
		addSubGrid();// add sub to main

		String x = "easy";
		ModeFactory.setFactory(x, table);
		mode = ModeFactory.getInstance(table);
		mode.setPuzzle();
		mode.randomInvisible();

		createPaneAndLabel();
		addNumberToLabel();
		addLabelToPane();
		addPaneToSubGrid();

	}

	public void seperateMainGrid() {
		for (int row = 0; row < table.getSize(); row++) {
			mainGrid.getColumnConstraints().add(new ColumnConstraints(BASE * 3));
			mainGrid.getRowConstraints().add(new RowConstraints(BASE * 3));
		}
		mainGrid.setGridLinesVisible(true);
	}

	public void createSubGrid() {
		for (int row = 0; row < table.getSize(); row++) {
			for (int column = 0; column < table.getSize(); column++) {
				subGrid[column][row] = new GridPane();
			}
		}
	}

	public void modifySubGrid() {
		for (int row = 0; row < table.getSize(); row++) {
			for (int column = 0; column < table.getSize(); column++) {
				for (int times = 0; times < table.getSize(); times++) {
					subGrid[column][row].getColumnConstraints().add(new ColumnConstraints(BASE));
					subGrid[column][row].getRowConstraints().add(new RowConstraints(BASE));
				}
				subGrid[column][row].setGridLinesVisible(true);
			}
		}
	}

	public void addSubGrid() {
		for (int row = 0; row < table.getSize(); row++) {
			for (int column = 0; column < table.getSize(); column++) {
				mainGrid.add(subGrid[column][row], column, row);
			}
		}
	}

	public void createPaneAndLabel() {
		for (int row = 0; row < table.getSize() * table.getSize(); row++) {
			for (int column = 0; column < table.getSize() * table.getSize(); column++) {
				pane[column][row] = new Pane();
				pane[column][row].setPrefSize(BASE, BASE);
				label[column][row] = new Label();
				label[column][row].setPrefSize(BASE, BASE);
			}
		}
	}

	public void addLabelToPane() {
		for (int row = 0; row < table.getSize() * table.getSize(); row++) {
			for (int column = 0; column < table.getSize() * table.getSize(); column++) {
				pane[column][row].getChildren().add(label[column][row]);
			}
		}
	}

	public void addNumberToLabel() {
		int number;
		boolean show;
		for (int row = 0; row < table.getSize() * table.getSize(); row++) {
			for (int column = 0; column < table.getSize() * table.getSize(); column++) {
				 number = mode.getPuzzle().get(row).getList().get(column).getNumber();
				 show = mode.getPuzzle().get(row).getList().get(column).getLock();
				 if(show) {
					 label[column%3+(row%3)*3][column/3 + (row/3)*3].setText(Integer.toString(number));
					 label[column%3+(row%3)*3][column/3 + (row/3)*3].setAlignment(Pos.CENTER);
				 } else {
					 label[column%3+(row%3)*3][column/3 + (row/3)*3].setText("");
					 label[column%3+(row%3)*3][column/3 + (row/3)*3].setAlignment(Pos.CENTER);
				 }
			}
		}
		
	}

	public void addPaneToSubGrid() {
		for (int rowGrid = 0; rowGrid < table.getSize(); rowGrid++) {
			for (int columnGrid = 0; columnGrid < table.getSize(); columnGrid++) {//a
				for (int rowPane = 0; rowPane < table.getSize(); rowPane++) {//b
					for (int columnPane = 0; columnPane < table.getSize(); columnPane++) {//c
						subGrid[columnGrid][rowGrid].add(pane[(columnGrid*3)+columnPane][rowPane+(rowGrid*3)],
								columnPane, rowPane);
					}
				}
			}
		}
	}
}