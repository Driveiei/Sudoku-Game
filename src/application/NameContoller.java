package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameContoller {
	@FXML
	TextField text;
	
	public void handleName(ActionEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("ModeMenu.fxml"));	
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
			}catch(IOException e) {
				System.err.println(e.getMessage());
			}
	}
	
	public void handleBack(ActionEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));	
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
			}catch(IOException e) {
				System.err.println(e.getMessage());
			}
	}
	
}
