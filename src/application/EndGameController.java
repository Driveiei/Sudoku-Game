package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RandomNumber;
import strategy.Mode;

public class EndGameController {

	@FXML
	Button playAgain;
	@FXML
	Button mainMenu;
	@FXML
	Button scoreboard;
	private ScoreManager manage;
	
	

	public void handlePlayAgain(ActionEvent ac) {
		try {
			Mode.getInstance().clearPuzzle();
			RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
			
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

	public void handleMainMenu(ActionEvent ac) {
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

	public void handleScoreboard(ActionEvent ac) {
		
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			ScoreboardController score = loader.getController();
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

	public void setScore(String name,String time) {
		manage = new ScoreManager(name,time) ;
		manage.recordScore();
		
		
	}
}
