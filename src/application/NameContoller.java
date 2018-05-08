package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NameContoller {
	@FXML
	TextField text;
	@FXML
	ImageView back;
	@FXML
	public void initialize() {
		back.setOnMouseEntered(in -> {
			back.setFitWidth(225);
			back.setFitHeight(175);
			back.setOnMouseExited(out ->{
				back.setFitWidth(200);
				back.setFitHeight(150);
			});
		});
	}
	public void handleNext(ActionEvent ac) {
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
	
	public void handleBack(MouseEvent ac) {
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
