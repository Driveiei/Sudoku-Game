package application;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScoreboardController {
	@FXML
	Label score1;
	@FXML
	Label score2;
	@FXML
	Label score3;
	@FXML
	Label score4;
	@FXML
	Label score5;
	
	private ScoreManager reader;

	public void initialize() {
		//System.out.println(reader.getsymbol());
//		List<Score> save = reader.readScore();
//		score1.setText(save.get(0).toString());
//		score2.setText(save.get(0).toString());
//		score3.setText(save.get(0).toString());
//		score4.setText(save.get(0).toString());
//		score5.setText(save.get(0).toString());
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

	public void setManager(ScoreManager reader) {
		this.reader = reader;
		List<Score> save = reader.readScore();
//		System.out.println(save);

	}
}
