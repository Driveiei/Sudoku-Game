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
import strategy.EasyStrategy;
import strategy.HardStrategy;
import strategy.Mode;

public class StageController {

	@FXML
	ImageView easy;

	@FXML
	ImageView hard;

	@FXML
	public void initialize() {
		effectImage(easy);
		effectImage(hard);
	}

	public void handleEasy(MouseEvent event) {
		Mode.setMode(new EasyStrategy());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GridUI.fxml"));
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

	public void handleHard(MouseEvent event) {
		Mode.setMode(new HardStrategy());
		try {
			// link controller part
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GridUI.fxml"));
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

	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in -> {
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseExited(out -> {
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}

	public void setRandomNumber(int table) {
		RandomNumber.setRandomNumber(table);

	}
}
