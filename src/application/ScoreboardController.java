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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreboardController {
	@FXML
	VBox box;

	private ScoreManager reader;
	private final int TOP_SCORE = 5;

	public void initialize() throws FileNotFoundException {
		reader = ScoreManager.getInstance();
		List<Score> save = reader.readScore();
		reader.sortTime(save);
		Label score;
		if (save.size() >= TOP_SCORE) {
			for (int i = 0; i < TOP_SCORE; i++) {
				score = new Label();
				score.setText(save.get(i).toString());
				box.getChildren().add(score);
				box.setAlignment(Pos.CENTER);
			}
		}
		else {
			for (int i = 0; i < save.size(); i++) {
				score = new Label();
				score.setText(save.get(i).toString());
				box.getChildren().add(score);
				box.setAlignment(Pos.CENTER);
			}
		}

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
}
