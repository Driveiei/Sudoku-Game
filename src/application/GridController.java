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

	private Grid grid;
	private List<GridPane> gridB;
	private List<Label> listText = new ArrayList<Label>();

	@FXML
	public void initialize() {
		grid = new Grid(3);
		pane.setCenter(grid.getGridMain());

	}

	public void handleShow(ActionEvent ac) {
		
	}
}