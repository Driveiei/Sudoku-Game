package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] arg) {
		launch(arg);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
		Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));	
		
		Scene scene = new Scene(pane);
		stage.setResizable(false);
		stage.setScene(scene);

		
		stage.show();
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

}