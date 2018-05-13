package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.BoxManager;
import logic.GridManager;
import logic.RandomNumber;
import strategy.Mode;

public class GridController {

	@FXML
	BorderPane borderPane;

	@FXML
	Label timer;
	
	@FXML
	ImageView assist;
	@FXML
	ImageView clear;
	@FXML
	ImageView done;
	@FXML
	ImageView restart;
	@FXML
	ImageView mainMenu;
	
	private static String time;

	private TimeTask worker;

	// Declare class
	private Grid griddy;
	private Mode mode;
	private SupportGrid support;

	// funny attribute
	private int size;
	private int realSize;


	@FXML
	public void initialize() {
		runTime();

		mode = Mode.getInstance();
		mode.setPuzzle();

		size = mode.getSize();
		realSize = size * size;

		support = new SupportGrid(size);

		griddy = new Grid(borderPane);
	}

	public void handleClear(MouseEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = support.adaptGrid(selectBox, selectGrid);
				int box = support.adaptBox(selectBox, selectGrid);
				BoxManager oneBox = puzzle.get(grid).getList().get(box);
				if (!oneBox.getLock())
					griddy.getLabel()[selectBox][selectGrid].setText("");
			}
		}
	}

	public void handleHint(MouseEvent event) {
		List<BoxManager> puzzle = new ArrayList<BoxManager>();
		puzzle.addAll(support.getSpaceBoxList());
		if (puzzle.size() == 0)
			return;
		int cursor = support.randomCursor(puzzle.size());
		BoxManager targetBox = puzzle.get(cursor);
		String target = Integer.toString(targetBox.getNumber());

		int numberOfGrid = -1;
		int numberOfBox = 0;
		while (++numberOfGrid < realSize) {
			numberOfBox = mode.getPuzzle().get(numberOfGrid).getList().indexOf(targetBox);
			if (numberOfBox >= 0) {
				break;
			}
		}

		int column = support.scaleToArrayColumn(numberOfGrid, numberOfBox);
		int row = support.scaleToArrayRow(numberOfGrid, numberOfBox);
		griddy.getLabel()[column][row].setText(target);
		griddy.getLabel()[column][row].setStyle("-fx-text-fill: rgb(256,128,278);");
		griddy.getLabel()[column][row].setFont(Font.font(null, FontWeight.BLACK, 32));
		mode.getPuzzle().get(numberOfGrid).getList().get(numberOfBox).setLock(true);
		griddy.removeButtonSelection(column, row);
	}

	public void handleDone(MouseEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		boolean check = true;
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = support.adaptGrid(selectBox, selectGrid);
				int box = support.adaptBox(selectBox, selectGrid);
				BoxManager oneBox = puzzle.get(grid).getList().get(box);
				if (!oneBox.getLock()) {
					String answerText = Integer.toString(oneBox.getNumber());
					if (griddy.getLabel()[selectBox][selectGrid].getText().equals(answerText))
						continue;
					else {
						check = false;
						break;
					}
				}
			}
			if (!check)
				break;
		}
		if (check) {
			time = timer.getText();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
				Parent pane = loader.load();
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void handleRestart(MouseEvent ac) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void handleMainMenu(MouseEvent ac) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static String getTime() {
		return time;
	}

	public void runTime() {
		worker = new TimeTask();
		timer.setText("00 : 00 : 000");
		timer.textProperty().bind(worker.messageProperty());
		new Thread(worker).start();

	}
}