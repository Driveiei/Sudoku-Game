package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.RandomNumber;

public class TableController {
	@FXML
	ImageView three;
	
	@FXML
	ImageView four;
	
	@FXML
	public void initialize() {
		effectImage(three);
		effectImage(four);
	}
	
	public void handleThree(MouseEvent event) {
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
	
	public void handleFour(MouseEvent event) {
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
	
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in ->{
			image.setFitWidth(350);
			image.setFitHeight(300);
			image.setOnMouseExited(out ->{
				image.setFitWidth(300);
				image.setFitHeight(200);
			});
		});
	}
}
