package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelpController {
	@FXML
	ImageView sudoku;
	@FXML
	ImageView greater;
	@FXML
	ImageView back;
	@FXML
	Pane data1;
	@FXML
	Pane data2;

	public void initialize() {
		effectImage(sudoku);
		effectImage(greater);
		effectImage(back);
	}
	
	public void handleSudoku(MouseEvent ac) {
		data1.setVisible(true);
		sudoku.setOnMouseExited(e->{
			data1.setVisible(false);
		});
	}

	public void handleGreater(MouseEvent ac) {
		data2.setVisible(true);
		greater.setOnMouseExited(e ->{
			data2.setVisible(false);
		});
	}
	
	public void handleBack(MouseEvent ac) {
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
	
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in ->{
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseReleased(out ->{
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}
}
