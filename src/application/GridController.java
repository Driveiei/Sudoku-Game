package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class GridController {
	@FXML
	BorderPane pane;

	private Grid grid;

	@FXML
	public void initialize() {
		grid = new Grid(3);
		pane.setCenter(grid.getGridMain());

	}

	public void handleShow(ActionEvent ac) {
		
	}
}