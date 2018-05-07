package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModeController {

	public void handleNext(ActionEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("TableMenu.fxml"));	
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			}catch(IOException e) {
				System.err.println(e.getMessage());
			}
		
	}
}
