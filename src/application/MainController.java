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

public class MainController {
	@FXML
	Button start;
	@FXML
	Button about;
	@FXML
	Button scoreborad;
	
	public void handleStart(ActionEvent ac) {
		try {
		Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));	
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("GridShow");
		stage.sizeToScene();
		stage.show();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public void handleAbout() {
		
	}

	public void handleScoreboard() {
	
}
}
