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
	List<GridPane> gridB = new ArrayList<GridPane>();
	List<Label> labelList = new ArrayList<Label>();

	@FXML
	public void initialize() {
		table = new Table(3); // 3 or 4
		random = new RandomNumber(table);
		random.run();

		gridA = createGridA(3);
		pane.setCenter(gridA);
		listOfGridB(3);
	}

	public GridPane createGridA(int num) {
		GridPane pane = new GridPane();
		for (int i = 0; i < num; i++) {
			ColumnConstraints column = new ColumnConstraints(225);
			pane.getColumnConstraints().add(column);
			RowConstraints rows = new RowConstraints(225);
			pane.getRowConstraints().add(rows);
		}
		pane.setGridLinesVisible(true);
		return pane;
	}

	public void listOfGridB(int num) {
		for (int i = 0; i < table.getList().size(); i++) {
			gridB.add(new GridPane());
		}

		for (int i = 0; i < table.getList().size(); i++) {
			GridPane x = gridB.get(i);
			for (int a = 0; a < num; a++) {
				ColumnConstraints column = new ColumnConstraints(75);
				x.getColumnConstraints().add(column);
				RowConstraints rows = new RowConstraints(75);
				x.getRowConstraints().add(rows);
			}
			x.setGridLinesVisible(true);
		}

		for (int i = 0; i < table.getList().size(); i++) {
			for (int j = 0; j < table.getList().size(); j++) {
				int number = table.getList().get(i).getList().get(j).getNumber();
				labelList.add(new Label(Integer.toString(number)));
			}
		}

		int fuck = 0;
		for (int a = 0; a < table.getSize(); a++) {
			for (int b = 0; b < table.getSize(); b++) {
				for (int i = 0; i < 9; i++) {
					gridB.get((3 * a) + b).add(labelList.get(fuck), (i % 3), (i / 3));
					fuck++;
				}

			}
		}

	}

	public void handleSomething(ActionEvent ac) {
		int fuck = 0;
		for (int a = 0; a < table.getSize(); a++) {
			for (int b = 0; b < table.getSize(); b++) {
				gridA.add(gridB.get(fuck), b, a);
				fuck++;
			}
		}
	}

}