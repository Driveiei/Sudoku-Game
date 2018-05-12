package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectScoreController {

	@FXML
	Button buttonThree;
	
	@FXML
	Button button3Easy;
	@FXML
	Button button3Hard;
	
	@FXML
	Button buttonFour;
	
	@FXML
	Button button4Easy;
	@FXML
	Button button4Hard;
	
	@FXML
	Button buttonGreater;
	
	
	private ScoreManager manage;
	
	public void initialize() {
		manage = new ScoreManager();
	}
	public void handleScoreThree(ActionEvent ac) {
		buttonThree.setVisible(false);
		button3Easy.setVisible(true);
		button3Hard.setVisible(true);
	}
	
	public void handleScoreThreeEasy(ActionEvent ac) {
		manage.setSymbol("+");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = new ScoreboardController();
			score.setManager(manage);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void handleScoreThreeHard(ActionEvent ac) {
		manage.setSymbol("@");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = new ScoreboardController();
			score.setManager(manage);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void handleScoreFour(ActionEvent ac) {
		buttonFour.setVisible(false);
		button4Easy.setVisible(true);
		button4Hard.setVisible(true);
	}
	
	public void handleScoreFourEasy(ActionEvent ac) {
		manage.setSymbol("+");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = new ScoreboardController();
			score.setManager(manage);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void handleScoreFourHard(ActionEvent ac) {
		manage.setSymbol("@");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = new ScoreboardController();
			score.setManager(manage);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void handleScoreGreaterThan(ActionEvent ac) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = new ScoreboardController();
			score.setManager(manage);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void handleBack(MouseEvent ac) {
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
}
