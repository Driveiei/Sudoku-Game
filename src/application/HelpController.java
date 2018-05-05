package application;

import java.io.IOException;

import com.sun.glass.events.MouseEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelpController {
	@FXML
	Label sudoku;

	@FXML
	Label greter;
	
	@FXML
	Button back;

	public void handleSudoku() {
		
	}

	public void handleGreter() {
		
	}
	
	public void handleBack(ActionEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));	
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
