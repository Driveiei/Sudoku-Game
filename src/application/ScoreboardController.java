package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreboardController {
	@FXML
	VBox box;
	@FXML
	ImageView mainMenu;

	private ScoreManager reader;
	private final int TOP_SCORE = 5;

	public void initialize() throws FileNotFoundException {
		mainMenu.setOnMouseEntered(in ->{
			mainMenu.setFitWidth(225);
			mainMenu.setFitHeight(175);
			mainMenu.setOnMouseExited(out ->{
				mainMenu.setFitWidth(200);
				mainMenu.setFitHeight(150);
			});
		});
		showScore();

	}

	public void handleMenu(MouseEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void showScore() {
		reader = ScoreManager.getInstance();
		List<Score> save;
		Label score;
		try {
			save = reader.readScore();
			reader.sortTime(save);
			if (save.size() >= TOP_SCORE) {
				for (int i = 0; i < TOP_SCORE; i++) {
					score = new Label();
					score.setText(String.format("%d) %3s",i+1,save.get(i).toString()));
					//score.setStyle("-fx-padding: 10;");
					score.setStyle("-fx-font: 32px \"Serif\";-fx-padding: 30;");
					box.getChildren().add(score);
					box.setAlignment(Pos.TOP_LEFT);
				}
			}
			else {
				for (int i = 0; i < save.size(); i++) {
					score = new Label();
					score.setText(String.format("%d) %s",i+1,save.get(i).toString()));
					//score.setStyle("-fx-padding: 10;");
					score.setStyle("-fx-font: 32px \"Serif\";-fx-padding: 30;");
					box.getChildren().add(score);
					box.setAlignment(Pos.TOP_LEFT);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
