package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.RandomNumber;
import strategy.Mode;

public class EndGameController {

	@FXML
	ImageView playAgain;
	@FXML
	ImageView mainMenu;
	@FXML
	ImageView scoreboard;
	private ScoreManager manage;
	
	public void initialize() {
		effectImage(playAgain);
		effectImage(mainMenu);
		effectImage(scoreboard);
	}

	public void handlePlayAgain(MouseEvent ac) {
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

	public void handleScoreboard(MouseEvent ac) {
		
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
			Parent pane = loader.load();
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in ->{
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseExited(out ->{
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}

	public void setScore(String name,String time) {
		manage = ScoreManager.getInstance();
		manage.recordScore(name, time);
	}
}
