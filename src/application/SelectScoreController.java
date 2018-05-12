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

public class SelectScoreController {

	@FXML
	ImageView buttonThree;
	@FXML
	ImageView button3Easy;
	@FXML
	ImageView button3Hard;
	
	@FXML
	ImageView buttonFour;
	@FXML
	ImageView button4Easy;
	@FXML
	ImageView button4Hard;
	
	@FXML
	ImageView buttonGreater;
	@FXML 
	ImageView back;
	
	
	private ScoreManager manage;
	
	public void initialize() {
		effectImage(buttonThree);
		effectImage(button3Easy);
		effectImage(button3Hard);
		effectImage(buttonFour);
		effectImage(button4Easy);
		effectImage(button4Hard);
		effectImage(buttonGreater);
		effectImage(back);
	}
	public void handleScoreThree(MouseEvent ac) {
		buttonThree.setVisible(false);
		button3Easy.setVisible(true);
		button3Hard.setVisible(true);
	}
	
	public void handleScoreThreeEasy(MouseEvent ac) {
		ScoreManager.setSymbol("+");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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
	
	public void handleScoreThreeHard(MouseEvent ac) {
		ScoreManager.setSymbol("@");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	public void handleScoreFour(MouseEvent ac) {
		buttonFour.setVisible(false);
		button4Easy.setVisible(true);
		button4Hard.setVisible(true);
	}
	
	public void handleScoreFourEasy(MouseEvent ac) {
		ScoreManager.setSymbol("&");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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
	
	public void handleScoreFourHard(MouseEvent ac) {
		ScoreManager.setSymbol("*");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	public void handleScoreGreaterThan(MouseEvent ac) {
		ScoreManager.setSymbol("?");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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
}
