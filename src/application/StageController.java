package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import strategy.EasyStrategy;
import strategy.HardStrategy;
import strategy.Mode;

public class StageController {

	@FXML
	Label easy;
	
	@FXML
	Label hard;
	
	public void handleEasy(MouseEvent event) {
		Mode.setMode(new EasyStrategy());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));
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
			Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));
			//link controller part
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("GridUI.fxml"));
//		    loader.load();
//		    GridController grid = loader.getController();
//		    grid.setMode(new EasyStrategy());
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
