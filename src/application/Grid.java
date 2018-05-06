package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import logic.GridManager;
import logic.RandomNumber;
import logic.Table;
import strategy.ModeFactory;

public class Grid {
	@FXML
	GridPane mainGrid;

	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;

	private Table table;
	private RandomNumber random;
	private ModeFactory mode;

	private final int BASE = 50;

	public Grid() {
		table = new Table(3);
		random = new RandomNumber(table);

		subGrid = new GridPane[3][3];// create
		modifySubGrid();
		addSubGrid();// add sub to main

		pane = new Pane[9][9];
		label = new Label[9][9];
		createPaneAndLabel();
		addPaneToSubGrid();
		addLabelToPane();
		addNumberToLabel();

		String x = "easy";
		ModeFactory.setFactory(x, table);
		mode = ModeFactory.getInstance(table);
		mode.setPuzzle();
		mode.randomInvisible();
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
				for(int times = 0; times < table.getSize(); times++) {
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
//				pane[column][row].setVisible(true);
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
		System.out.println("table : " + table.getSize());
		for (int row = 0; row < table.getSize() * table.getSize(); row++) {
			for (int column = 0; column < table.getSize() * table.getSize(); column++) {
				number = mode.getPuzzle().get(row).getList().get(column).getNumber();
				label[column][row].setText(Integer.toString(number));
				System.out.println("xxx");
				System.out.println(number);
			}
		}

		// List<Label> listLabel = new ArrayList<Label>();
		// for (int i = 0; i < table.getList().size(); i++) {
		// for (int j = 0; j < table.getList().size(); j++) {
		// if(table.getList().get(i).getList().get(j).getLock()) {
		// int number = table.getList().get(i).getList().get(j).getNumber();
		// listLabel.add(new Label(Integer.toString(number)));
		// } else {
		// listLabel.add(new Label(""));
		// }
		// }
		// }
	}

	public void addPaneToSubGrid() {
		for (int rowGrid = 0; rowGrid < table.getSize(); rowGrid++) {
			for (int columnGrid = 0; columnGrid < table.getSize(); columnGrid++) {
				for (int rowPane = 0; rowGrid < table.getSize(); rowGrid++) {
					for (int columnPane = 0; columnPane < table.getSize(); columnPane++) {
						subGrid[columnGrid][rowGrid].add(pane[rowPane * table.getSize() + columnPane][rowPane],
								rowPane * table.getSize() + columnPane, rowPane);
					}
				}
			}
		}
	}

	// private Table table;
	// private RandomNumber random;
	//
	// private GridPane gridA;
	// private List<GridPane> gridB;
	// private List<Label> listText;
	// private int BASE = 50;
	// private ModeFactory mode;
	//
	// public Grid(int num) {
	// if(num == 4) {
	// this.BASE = 30;
	// }
	//
	// table = new Table(num);
	// random = new RandomNumber(table);
	// random.run();
	//
	// String x = "easy";
	// ModeFactory.setFactory(x);
	// mode = ModeFactory.getInstance();
	// mode.setPuzzle();
	// mode.randomInvisible();
	//
	// create();
	//
	//
	// }
	//
	// public void create() {
	// gridA = createGridA();
	// gridB = createGridB();
	// listText = addLabel();
	// createGridC();
	//
	// int pointer = 0;
	// for (int a = 0; a < table.getSize(); a++) {
	// for (int b = 0; b < table.getSize(); b++) {
	// gridA.add(gridB.get(pointer), b, a);
	// pointer++;
	// }
	// }
	// addNumber();
	// }
	//
	// public Table getTable() {
	// return table;
	// }
	//
	// public GridPane getGridMain() {
	// return gridA;
	// }
	//
	// public List<GridPane> getGridMinor() {
	// return gridB;
	// }
	//
	// public List<Label> getLabel() {
	// return listText;
	//
	// }
	//
	// public void emtyGrid(GridPane pane, int size) {
	// for (int i = 0; i < table.getSize(); i++) {
	// ColumnConstraints column = new ColumnConstraints(size);
	// pane.getColumnConstraints().add(column);
	// RowConstraints rows = new RowConstraints(size);
	// pane.getRowConstraints().add(rows);
	// }
	// pane.setGridLinesVisible(true);
	// }
	//
	// public GridPane createGridA() {
	// GridPane pane = new GridPane();
	// emtyGrid(pane, BASE * table.getSize());
	// return pane;
	// }
	//
	// public List<GridPane> createGridB() {
	// List<GridPane> listGrid = new ArrayList<GridPane>();
	// for (int i = 0; i < table.getList().size(); i++) {
	// listGrid.add(new GridPane());
	// }
	// return listGrid;
	// }
	//
	// public void createGridC() {
	// for (int i = 0; i < table.getList().size(); i++) {
	// emtyGrid(gridB.get(i), BASE);
	// }
	//
	// }
	//
	// public List<Label> addLabel() {
	// List<Label> listLabel = new ArrayList<Label>();
	// for (int i = 0; i < table.getList().size(); i++) {
	// for (int j = 0; j < table.getList().size(); j++) {
	// if(table.getList().get(i).getList().get(j).getLock()) {
	// int number = table.getList().get(i).getList().get(j).getNumber();
	// listLabel.add(new Label(Integer.toString(number)));
	// } else {
	// listLabel.add(new Label(""));
	// }
	// }
	// }
	// return listLabel;
	// }
	//
	// public void addNumber() {
	// int pointer = 0;
	// int tableSize = table.getSize();
	// for (int a = 0; a < tableSize; a++) {
	// for (int b = 0; b < tableSize; b++) {
	// for (int i = 0; i < table.getList().size(); i++) {
	// gridB.get((tableSize * a) + b).add(listText.get(pointer), (i % tableSize), (i
	// / tableSize));
	// pointer++;
	// }
	//
	// }
	// }
	// }

}
