package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import logic.RandomNumber;
import logic.Table;

public class Grid {

	private Table table;
	private RandomNumber random;

	private GridPane gridA;
	private List<GridPane> gridB;
	private List<Label> listText = new ArrayList<Label>();
	private int BASE = 50;

	public Grid(int num) {
		if(num == 4) {
			this.BASE = 30;
		}
		table = new Table(num);
		random = new RandomNumber(table);
		random.run();

		create();

	}

	public void create() {
		gridA = createGridA();
		gridB = createGridB();
		listText = addLabel();
		createGridC();

		int pointer = 0;
		for (int a = 0; a < table.getSize(); a++) {
			for (int b = 0; b < table.getSize(); b++) {
				gridA.add(gridB.get(pointer), b, a);
				pointer++;
			}
		}
		addNumber();
	}

	public Table getTable() {
		return table;
	}

	public GridPane getGridMain() {
		return gridA;
	}

	public List<GridPane> getGridMinor() {
		return gridB;
	}

	public List<Label> getLabel() {
		return listText;

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

	public List<GridPane> createGridB() {
		List<GridPane> listGrid = new ArrayList<GridPane>();
		for (int i = 0; i < table.getList().size(); i++) {
			listGrid.add(new GridPane());
		}
		return listGrid;
	}

	public void createGridC() {
		for (int i = 0; i < table.getList().size(); i++) {
			emtyGrid(gridB.get(i), BASE);
		}

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
					gridB.get((tableSize * a) + b).add(listText.get(pointer), (i % tableSize), (i / tableSize));
					pointer++;
				}

			}
		}
	}

}
