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
	ImageView next;
	private static String name;
	@FXML
	public void initialize() {
		effectImage(back);
		effectImage(next);
	}
	
	
	public void handlEnter(ActionEvent ac) {
		name = text.getText();
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
	
	public void handleNext(MouseEvent ac) {
		name = text.getText();
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
	
	public static String getName() {
		return name;
	}
	
	
}
