package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
//	@FXML
//	BorderPane pane;
	
	@FXML
	GridPane mainGrid;
//
//	private Grid grid;
//	private Save save;
	
	private GridPane[][] subGrid;
	private Pane[][] pane;
	private Label[][] label;
	
	private Table table;
	private RandomNumber random;
	private ModeFactory mode;
	
	private final int BASE = 100;
	
	@FXML
	public void initialize() {
//		grid = new Grid(save.getNum());
//		pane.setCenter(grid.getGridMain());
		table = new Table(3);
		random = new RandomNumber(table);
		random.run();
		
		subGrid = new GridPane[3][3];// create
		createSubGrid();
		modifySubGrid(); 
		addSubGrid();//add sub to main
		
		pane = new Pane[9][9];
		label = new Label[9][9];
		createPaneAndLabel();
		addLabelToPane();
		
		String x = "easy";
		ModeFactory.setFactory(x,table);
		mode = ModeFactory.getInstance(table); 
		mode.setPuzzle();
		mode.randomInvisible();	
	}
	
	public void createSubGrid() {
		for(int row = 0; row < table.getSize(); row++) {
			for(int column =0; column < table.getSize(); column ++) {
				subGrid[column][row] = new GridPane();
			}
		}
	}
	
	public void modifySubGrid() {
		for (int row = 0; row < table.getSize(); row++) {
			for (int column = 0; column < table.getSize(); column++) {
				ColumnConstraints oneColumn = new ColumnConstraints(BASE);
				subGrid[column][row].getColumnConstraints().add(oneColumn);
				RowConstraints oneRow = new RowConstraints(BASE);
				subGrid[column][row].getRowConstraints().add(oneRow);
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
		for (int row = 0; row < table.getSize()*table.getSize(); row++) {
			for (int column = 0; column < table.getSize()*table.getSize(); column++) {
				pane[column][row] = new Pane();
				pane[column][row].setPrefSize(BASE,BASE);
				pane[column][row]
				
				label[column][row] = new Label();
				label[column][row].setPrefSize(BASE, BASE);
				
			}
		}
	}
	
	public void addLabelToPane() {
		for (int row = 0; row < table.getSize()*table.getSize(); row++) {
			for (int column = 0; column < table.getSize()*table.getSize(); column++) {
				pane[column][row].getChildren().add(label[column][row]);
			}
		}
	}
	
	public void addNumberToLabel() {
		int number;
		for (int row = 0; row < table.getSize()*table.getSize(); row++) {
			for (int column = 0; column < table.getSize()*table.getSize(); column++) {
				number = mode.getPuzzle().get(row).getList().get(column).getNumber();
				label[column][row].setText(Integer.toString(number));
			}
		}
	}
	
	

//	public void handleDone(ActionEvent ac) {
//		try {
//			Parent pane = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
//			Scene scene = new Scene(pane);
//			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
//			stage.setScene(scene);
//			stage.setResizable(false);
//			stage.show();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
//	}
}