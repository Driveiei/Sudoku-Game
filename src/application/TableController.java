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

public class TableController {
	@FXML
	Button three;
	
	@FXML
	Button four;
	
	public void handleThree(ActionEvent event) {
		RandomNumber.setRandomNumber(3);
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("StageMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void handleFour(ActionEvent event) {
		RandomNumber.setRandomNumber(4);
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("StageMenu.fxml"));
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
