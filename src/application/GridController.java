package application;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.tk.Toolkit;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.Point;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
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
	private Pane supportPane;
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
		supportPane = new Pane();
		supportPane.getChildren().add(mainGrid);
		borderPane.setCenter(supportPane);
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
				if (show) {
					label[column % 3 + (row % 3) * 3][column / 3 + (row / 3) * 3].setText(Integer.toString(number));
					label[column % 3 + (row % 3) * 3][column / 3 + (row / 3) * 3].setAlignment(Pos.CENTER);
				} else {
					label[column % 3 + (row % 3) * 3][column / 3 + (row / 3) * 3].setText("");
					label[column % 3 + (row % 3) * 3][column / 3 + (row / 3) * 3].setAlignment(Pos.CENTER);

					// Create selection
					label[column % 3 + (row % 3) * 3][column / 3 + (row / 3) * 3].setOnMousePressed(e -> {
						Pane a = new Pane();
						a.setPrefSize(BASE * table.getSize(), BASE * table.getSize());
						GridPane grid = new GridPane();
						
						a.setTranslateX( e.getSceneX()-a.getPrefWidth()/2);
						a.setTranslateY( e.getSceneY()-a.getPrefHeight()/2);

						// button list
						List<Button> button = new ArrayList<Button>();
						for (int size = 0; size < table.getSize() * table.getSize(); size++) {
							button.add(new Button());
							button.get(size).setText(Integer.toString(size + 1));
						}
						a.getChildren().add(grid);
						borderPane.getChildren().add(a);
						a.setVisible(true);
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
								grid.add(button.get((table.getSize() * i) + j), j, i);
								button.get((table.getSize() * i) + j).setMaxSize(BASE, BASE);

								button.get((table.getSize() * i) + j).setOnMouseClicked(c -> {
									
									borderPane.getChildren().remove(a);

								});
							}
						}
						
						a.setOnMouseExited(z -> {
							borderPane.getChildren().remove(a);				});

					});
				}

			}

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
}