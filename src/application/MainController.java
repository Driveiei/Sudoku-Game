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

public class MainController {
	@FXML
	ImageView start;
	@FXML
	ImageView about;
	@FXML
	ImageView scoreborad;
	@FXML
	public void initialize() {
		effectImage(start);
		effectImage(about);
		effectImage(scoreborad);
	}
	
	public void handleStart(MouseEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("NameMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	public void handleAbout(MouseEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("Help.fxml"));
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
			Parent pane = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
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
