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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import strategy.Mode;

public class Grid {

	private BorderPane borderPane;

	private Pane supportPane;
	private GridPane mainGrid;
	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;

	private List<Button> buttonList;

	private Mode mode;
	private GreaterThanLabel great;
	private Pane greaterPane;

	private int BASE;
	private int size;
	private int realSize;

	public Grid(BorderPane borderPane) {
		this.borderPane = borderPane;
		mode = Mode.getInstance();
		this.BASE = mode.getBase();

		size = mode.getSize();
		realSize = size * size;

		mainGrid = new GridPane();
		seperateMainGrid();
		supportPane = new Pane();
		supportPane.getChildren().add(mainGrid);
		borderPane.setCenter(supportPane);
		mainGrid.setAlignment(Pos.CENTER);

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
		
		greaterPane = new Pane();
		great = new GreaterThanLabel(greaterPane);
		supportPane.getChildren().add(greaterPane);
	}

	public void seperateMainGrid() {
		for (int row = 0; row < size; row++) {
			mainGrid.getColumnConstraints().add(new ColumnConstraints(BASE * size));
			mainGrid.getRowConstraints().add(new RowConstraints(BASE * size));
		}
		mainGrid.setGridLinesVisible(true);
	}

	public void createSubGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				subGrid[column][row] = new GridPane();
			}
		}
	}

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

	public void addSubGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				mainGrid.add(subGrid[column][row], column, row);
			}
		}
	}

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

	public void addLabelToPane() {
		for (int row = 0; row < realSize; row++) {
			for (int column = 0; column < realSize; column++) {
				pane[column][row].getChildren().add(label[column][row]);
			}
		}
	}

	public void selectionButton(int column, int row) {
		label[changeColumnScale(column, row)][changeRowScale(column, row)].setOnMousePressed(event -> {
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
					buttonList.get((size * i) + j).setOnMouseClicked(event2 -> {

						Button button = (Button) event2.getSource();
						((Labeled) event.getSource()).setText(button.getText());
						((Labeled) event.getSource()).setStyle("-fx-text-fill: rgb(240,128,128);");
						((Labeled) event.getSource()).setFont(Font.font(null, FontWeight.LIGHT, 32));
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

	public void removeButtonSelection(int column,int row) {
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

	public int changeColumnScale(int column, int row) {
		return column % size + (row % size) * size;
	}

	public int changeRowScale(int column, int row) {
		return column / size + (row / size) * size;
	}

	public void setMiniGrid() {
		for (int size = 0; size < realSize; size++) {
			buttonList.add(new Button());
			buttonList.get(size).setText(Integer.toString(size + 1));
		}
	}

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

	public Label[][] getLabel() {
		return label;
	}
}