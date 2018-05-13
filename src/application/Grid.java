package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.RandomNumber;
import strategy.Mode;
/**
 * Grid represents production by take BorderPane of GridController then add all of function
 * that sudoku should have.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 *
 */
public class Grid {
	
	/** Declare class*/
	private BorderPane borderPane;
	private Pane supportPane;
	private GridPane mainGrid;
	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;
	private List<Button> buttonList;
	private Mode mode;
	private Pane greaterPane;
	
	/** Declare   attribute */
	private int BASE;
	private int size;
	private int realSize;

	/**Initialize class and set default.
	 * 
	 * @param borderPane form GridController
	 */
	public Grid(BorderPane borderPane) {
		this.borderPane = borderPane;
		mode = Mode.getInstance();
		this.BASE = mode.getBase();

		size = mode.getSize();
		realSize = size * size;

		mainGrid = new GridPane();
		separateMainGrid();
		supportPane = new Pane();
		addGreater();
		supportPane.getChildren().add(mainGrid);
		borderPane.setCenter(supportPane);
		mainGrid.setAlignment(Pos.CENTER);
		supportPane
				.setStyle("-fx-background-color: white;-fx-border-color: black;-fx-border-width: 5;-fx-opacity: 0.9;");
		subGrid = new GridPane[size][size];// create
		pane = new Pane[realSize][realSize];
		label = new Label[realSize][realSize];
		buttonList = new ArrayList<Button>();

		createSubGrid();
		modifySubGrid();
		addSubGrid();

		createPaneAndLabel();
		addNumberToLabel();
		addLabelToPane();
		addPaneToSubGrid();
	}
	/**Separate MainGrid by size of puzzle.*/
	public void separateMainGrid() {
		for (int row = 0; row < size; row++) {
			mainGrid.getColumnConstraints().add(new ColumnConstraints(BASE * size));
			mainGrid.getRowConstraints().add(new RowConstraints(BASE * size));
		}
		mainGrid.setGridLinesVisible(true);
	}
	/**Create number of GridPane by array2D.*/
	public void createSubGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				subGrid[column][row] = new GridPane();
			}
		}
	}
	/**Separate SubGrid by size of puzzle.*/
	public void modifySubGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				for (int times = 0; times < size; times++) {
					subGrid[column][row].getColumnConstraints().add(new ColumnConstraints(BASE));
					subGrid[column][row].getRowConstraints().add(new RowConstraints(BASE));
				}
				subGrid[column][row].setGridLinesVisible(true);
			}
		}
	}
	/**Add SubGrid to MainGrid*/
	public void addSubGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				mainGrid.add(subGrid[column][row], column, row);
			}
		}
	}
	/**Create Pane and Label by number of puzzle box*/
	public void createPaneAndLabel() {
		for (int row = 0; row < realSize; row++) {
			for (int column = 0; column < realSize; column++) {
				pane[column][row] = new Pane();
				pane[column][row].setPrefSize(BASE, BASE);
				label[column][row] = new Label();
				label[column][row].setPrefSize(BASE, BASE);
			}
		}
	}
	/**Add Label to Pane*/
	public void addLabelToPane() {
		for (int row = 0; row < realSize; row++) {
			for (int column = 0; column < realSize; column++) {
				pane[column][row].getChildren().add(label[column][row]);
			}
		}
	}
	/**This method is show box of number that player want input to game.
	 * included event when player need to lock number(right-click to number) from clear and hint method.
	 * 
	 * @param column 
	 * @param row
	 */
	public void selectionButton(int column, int row) {
		//when player click on empty label it'll show mini box that contains number.
		label[changeColumnScale(column, row)][changeRowScale(column, row)].setOnMousePressed(event -> {
			if(event.getButton() == MouseButton.PRIMARY) {	
			Pane miniPane = new Pane();
			miniPane.setPrefSize(BASE * size, BASE * size);
			GridPane grid = new GridPane();
			miniPane.setTranslateX(event.getSceneX() - miniPane.getPrefWidth() / 2);
			miniPane.setTranslateY(event.getSceneY() - miniPane.getPrefHeight() / 2);
			setMiniGrid();
			miniPane.getChildren().add(grid);

			borderPane.getChildren().add(miniPane);
			miniPane.setVisible(true);
			// create 3*3 on gridpane
			grid.setPrefSize(BASE, BASE);
			for (int i = 0; i < size; i++) {
				grid.getColumnConstraints().add(new ColumnConstraints(BASE));
				grid.getRowConstraints().add(new RowConstraints(BASE));
				grid.setGridLinesVisible(true);
			}

			// add button to grid
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					grid.add(buttonList.get((size * i) + j), j, i);
					buttonList.get((size * i) + j).setMaxSize(BASE, BASE);
					//click button will setText of empty label.
					buttonList.get((size * i) + j).setOnMouseClicked(event2 -> {

						Button button = (Button) event2.getSource();
						((Labeled) event.getSource()).setText(button.getText());
						((Labeled) event.getSource()).setStyle("-fx-text-fill: rgb(21, 138, 0);");
						((Labeled) event.getSource()).setFont(Font.font(null, FontWeight.BOLD, 32));
						mode.getPuzzle().get(row).getList().get(column).setLock(false);
						try {
							borderPane.getChildren().remove(miniPane);
						} catch (IndexOutOfBoundsException ex) {

						}
					});
				}
			}
			//when mouse position out of border.
			miniPane.setOnMouseExited(z -> {
				borderPane.getChildren().remove(miniPane);
			});
		}	//if player right-click to label that have number it's will lock.
			else if(event.getButton() == MouseButton.SECONDARY) {
				if(!label[changeColumnScale(column, row)][changeRowScale(column, row)].getText().equals("")) {
					label[changeColumnScale(column, row)][changeRowScale(column, row)].setStyle("-fx-background-insets: 20;-fx-background-color: rgb(240,128,128);-fx-background-radius: 200;");
					mode.getPuzzle().get(row).getList().get(column).setLock(true);
				}
			}
		});

	}
	/**
	 * 
	 * @param column of grid
	 * @param row of grid
	 */
	public void removeButtonSelection(int column, int row) {
		label[column][row].setOnMousePressed(null);
	}

	public void addNumberToLabel() {
		int number;
		boolean show;
		for (int row = 0; row < realSize; row++) {
			for (int column = 0; column < realSize; column++) {
				number = mode.getPuzzle().get(row).getList().get(column).getNumber();
				show = mode.getPuzzle().get(row).getList().get(column).getLock();
				if (show) {
					label[changeColumnScale(column, row)][changeRowScale(column, row)]
							.setText(Integer.toString(number));
					label[changeColumnScale(column, row)][changeRowScale(column, row)]
							.setFont(Font.font(null, FontWeight.BLACK, 32));
				} else {
					label[changeColumnScale(column, row)][changeRowScale(column, row)].setText("");
					selectionButton(column, row);
				}
				label[changeColumnScale(column, row)][changeRowScale(column, row)].setAlignment(Pos.CENTER);
			}

		}
	}
	/**
	 *  converts array2D to List
	 * @param column of grid
	 * @param row of grid
	 * @return
	 */
	public int changeColumnScale(int column, int row) {
		return column % size + (row % size) * size;
	}
	/**
	 *  converts array2D to List
	 * @param column of grid
	 * @param row of grid
	 * @return
	 */
	public int changeRowScale(int column, int row) {
		return column / size + (row / size) * size;
	}
	/**
	 * set Number to button(set on selectButton).
	 */
	public void setMiniGrid() {
		for (int size = 0; size < realSize; size++) {
			buttonList.add(new Button());
			buttonList.get(size).setText(Integer.toString(size + 1));
		}
	}
	/**
	 * add Pane that contains all of numbers in game to SubGrid.
	 */
	public void addPaneToSubGrid() {
		for (int rowGrid = 0; rowGrid < size; rowGrid++) {
			for (int columnGrid = 0; columnGrid < size; columnGrid++) {// a
				for (int rowPane = 0; rowPane < size; rowPane++) {// b
					for (int columnPane = 0; columnPane < size; columnPane++) {// c
						subGrid[columnGrid][rowGrid].add(
								pane[(columnGrid * size) + columnPane][rowPane + (rowGrid * size)], columnPane,
								rowPane);
					}
				}
			}
		}
	}
	/**
	 * This method for mode SudokuGreaterThan it's will create symbol(<,>,^,v).
	 */
	public void addGreater() {
		if (Mode.getInstance().getClass().getName().equals("strategy.GreaterThanStrategy")) {
			greaterPane = new Pane();
			new GreaterThanLabel(greaterPane);
			supportPane.getChildren().add(greaterPane);
		}
	}
	/**Get label2D for used in controller.
	 * 
	 * @return label
	 */
	public Label[][] getLabel() {
		return label;
	}
}