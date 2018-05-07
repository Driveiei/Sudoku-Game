package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import logic.Table;
import strategy.ModeFactory;

public class Grid {

	private BorderPane borderPane;

	private Pane supportPane;
	private GridPane mainGrid;
	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;

	private List<Button> buttonList;

	private Table table;
	private ModeFactory mode;

	private final int BASE = 75;

	public Grid(BorderPane borderPane, Table table, ModeFactory mode) {
		this.borderPane = borderPane;
		this.table = table;
		this.mode = mode;

		mainGrid = new GridPane();
		seperateMainGrid();
		supportPane = new Pane();
		supportPane.getChildren().add(mainGrid);
		borderPane.setCenter(supportPane);
		mainGrid.setAlignment(Pos.CENTER);

		subGrid = new GridPane[3][3];// create
		pane = new Pane[9][9];
		label = new Label[9][9];
		buttonList = new ArrayList<Button>();

		createSubGrid();
		modifySubGrid();
		addSubGrid();// add sub to main

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

	public void selectionButton(int column, int row) {
		label[changeColumnScale(column, row)][changeRowScale(column, row)].setOnMousePressed(event -> {
			Pane miniPane = new Pane();
			miniPane.setPrefSize(BASE * table.getSize(), BASE * table.getSize());
			GridPane grid = new GridPane();
			miniPane.setTranslateX(event.getSceneX() - miniPane.getPrefWidth() / 2);
			miniPane.setTranslateY(event.getSceneY() - miniPane.getPrefHeight() / 2);
			setMiniGrid();
			miniPane.getChildren().add(grid);
			borderPane.getChildren().add(miniPane);
			miniPane.setVisible(true);
			// create 3*3 on gridpane
			grid.setPrefSize(BASE, BASE);
			for (int i = 0; i < table.getSize(); i++) {
				grid.getColumnConstraints().add(new ColumnConstraints(BASE));
				grid.getRowConstraints().add(new RowConstraints(BASE));
				grid.setGridLinesVisible(true);
			}

			// add button to grid
			for (int i = 0; i < table.getSize(); i++) {
				for (int j = 0; j < table.getSize(); j++) {
					grid.add(buttonList.get((table.getSize() * i) + j), j, i);
					buttonList.get((table.getSize() * i) + j).setMaxSize(BASE, BASE);
					buttonList.get((table.getSize() * i) + j).setOnMouseClicked(event2 -> {

						Button button = (Button) event2.getSource();
						((Labeled) event.getSource()).setText(button.getText());
						((Labeled) event.getSource()).setStyle("-fx-text-fill: #483d8b");
						try {
							borderPane.getChildren().remove(miniPane);
						} catch (IndexOutOfBoundsException ex) {

						}
					});
				}
			}
			miniPane.setOnMouseExited(z -> {
				borderPane.getChildren().remove(miniPane);
			});
		});
	}

	public void addNumberToLabel() {
		int number;
		boolean show;
		for (int row = 0; row < table.getSize() * table.getSize(); row++) {
			for (int column = 0; column < table.getSize() * table.getSize(); column++) {
				number = mode.getPuzzle().get(row).getList().get(column).getNumber();
				show = mode.getPuzzle().get(row).getList().get(column).getLock();
				if (show) {
					label[changeColumnScale(column, row)][changeRowScale(column, row)]
							.setText(Integer.toString(number));
				} else {
					label[changeColumnScale(column, row)][changeRowScale(column, row)].setText("");
					selectionButton(column, row);
				}
				label[changeColumnScale(column, row)][changeRowScale(column, row)].setAlignment(Pos.CENTER);
			}

		}
	}

	public int changeColumnScale(int column, int row) {
		return column % 3 + (row % 3) * 3;
	}

	public int changeRowScale(int column, int row) {
		return column / 3 + (row / 3) * 3;
	}

	public void setMiniGrid() {
		for (int size = 0; size < table.getSize() * table.getSize(); size++) {
			buttonList.add(new Button());
			buttonList.get(size).setText(Integer.toString(size + 1));
		}
	}

	public void addPaneToSubGrid() {
		for (int rowGrid = 0; rowGrid < table.getSize(); rowGrid++) {
			for (int columnGrid = 0; columnGrid < table.getSize(); columnGrid++) {// a
				for (int rowPane = 0; rowPane < table.getSize(); rowPane++) {// b
					for (int columnPane = 0; columnPane < table.getSize(); columnPane++) {// c
						subGrid[columnGrid][rowGrid].add(pane[(columnGrid * 3) + columnPane][rowPane + (rowGrid * 3)],
								columnPane, rowPane);
					}
				}
			}
		}
	}
	
	public Label[][] getLabel(){
		return label;
	}

}