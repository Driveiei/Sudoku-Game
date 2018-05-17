package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * NameController for events and initializing components with two modes(Basic
 * Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class NameContoller {
	/**
	 * Text to save on scoreboard.
	 */
	private static String name;

	/**
	 * Text input component that allows a user to enter a single line of unformatted
	 * text.
	 */
	@FXML
	TextField text;
	/**
	 * Node used for painting images on 'Back' button loaded with Image class.
	 */
	@FXML
	ImageView back;
	/**
	 * Node used for painting images on 'Next' button loaded with Image class.
	 */
	@FXML
	ImageView next;
	@FXML
	Label notify;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		effectImage(back);
		effectImage(next);
		notify.setVisible(false);
	}

	/**
	 * Link with "ModeMenu.fxml" to switch scene to other scene for selecting size
	 * of game and manipulate user to type name must more than zero character but,
	 * less than ten characters.
	 * 
	 * @param event - An Event representing some type of action. This event type is
	 *            widely used to represent a variety of things, such as when a
	 *            Button has been fired
	 */
	public void handleEnter(ActionEvent event) {
		if (!text.getText().equals("") && text.getText().length() <= 9) {
			name = text.getText();
			try {
				Parent pane = FXMLLoader.load(getClass().getResource("ModeMenu.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setResizable(true);
				stage.show();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		else {
			notify.setVisible(true);
		}
	}

	/**
	 * Link with "ModeMenu.fxml" to switch scene to other scene for selecting size
	 * of game and manipulate user to type name must more than zero character but,
	 * less than ten characters.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleNext(MouseEvent event) {
		if (!text.getText().equals("") && text.getText().length() <= 9) {
			name = text.getText();
			try {
				Parent pane = FXMLLoader.load(getClass().getResource("ModeMenu.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setResizable(true);
				stage.show();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}else {
			notify.setVisible(true);
		}
	}

	/**
	 * Link with "MainMenu.fxml" to switch scene to other scene for back to MainMenu game.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleBack(MouseEvent ac) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Makes the picture zoom in/out when mouse cursor enters on the button.
	 * 
	 * @param image - is a Node used for painting images loaded with Image class. 
	 */
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in -> {
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseExited(out -> {
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}
	
	/**
	 * Get name of user input.
	 * 
	 * @return name of user input.
	 * */
	public static String getName() {
		return name;
	}

}
