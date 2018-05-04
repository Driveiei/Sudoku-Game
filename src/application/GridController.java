package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.RandomNumber;
import logic.Table;

public class GridController {
	@FXML
	BorderPane pane;

	private Table table;
	private RandomNumber random;
	private GridPane gridA;
	private List<GridPane> gridB;
	private List<Label> listText = new ArrayList<Label>();
	private final int BASE = 50;

	@FXML
	public void initialize() {
		table = new Table(3); // 3 or 4
		random = new RandomNumber(table);
		random.run();

		gridA = createGridA();
		gridB = addGridB();
		pane.setCenter(gridA);
		listText = addLabel();
		createGridB();

	}

	public void emtyGrid(GridPane pane, int size) {
		for (int i = 0; i < table.getSize(); i++) {
			ColumnConstraints column = new ColumnConstraints(size);
			pane.getColumnConstraints().add(column);
			RowConstraints rows = new RowConstraints(size);
			pane.getRowConstraints().add(rows);
		}
		pane.setGridLinesVisible(true);
	}

	public GridPane createGridA() {
		GridPane pane = new GridPane();
		emtyGrid(pane, BASE * table.getSize());
		return pane;
	}

	public void createGridB() {
		for (int i = 0; i < table.getList().size(); i++) {
			emtyGrid(gridB.get(i), BASE);
		}

	}

	public List<GridPane> addGridB() {
		List<GridPane> listGrid = new ArrayList<GridPane>();
		for (int i = 0; i < table.getList().size(); i++) {
			listGrid.add(new GridPane());
		}
		return listGrid;

	}

	public List<Label> addLabel() {
		List<Label> listLabel = new ArrayList<Label>();
		for (int i = 0; i < table.getList().size(); i++) {
			for (int j = 0; j < table.getList().size(); j++) {
				int number = table.getList().get(i).getList().get(j).getNumber();
				listLabel.add(new Label(Integer.toString(number)));
			}
		}
		return listLabel;
	}

	public void addNumber() {
		int pointer = 0;
		int tableSize = table.getSize();
		for (int a = 0; a < tableSize; a++) {
			for (int b = 0; b < tableSize; b++) {
				for (int i = 0; i < table.getList().size(); i++) {
					System.out.println("Number is " + table.getList().size());
					gridB.get((tableSize * a) + b).add(listText.get(pointer), (i % tableSize), (i / tableSize));
					System.out.println("Number is " + (tableSize * a) + b);
					pointer++;
				}

			}
		}
	}

	public void handleShow(ActionEvent ac) {
		int pointer = 0;
		for (int a = 0; a < table.getSize(); a++) {
			for (int b = 0; b < table.getSize(); b++) {
				gridA.add(gridB.get(pointer), b, a);
				pointer++;
			}
		}
		addNumber();
	}
}