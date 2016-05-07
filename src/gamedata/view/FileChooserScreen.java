//This entire file is part of my masterpiece.
// Michael Figueiras, The Loops Goat Cheese Salad
/**
 * See BaseScreen.java in the gameplayer.screen package for a detailed explanation of how the refactoring of the 
 * Screen class is implemented in these examples.
 */

package gamedata.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gui.view.ComboBoxGame;
import gui.view.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

/**
 * This screen acts as an intermediary between program functions that equips the
 * user with the opportunity to choose a new game for different purposes. It is
 * comprised of a simpel toolbar with a returnToSplash button and a combo box
 * that displays existing games available in the directory
 * 
 * @author cmt57, mdf15
 *
 */
public class FileChooserScreen extends Screen implements Observer {

	private static final String CHOOSER_RESOURCE = "fcGUI";
	private static final String FC_BUTTONS = "FCButtons";
	private static final int X_OFFSET = 100;
	private static final int BOX_X = SCREEN_WIDTH / 2;
	private static final int BOX_Y = SCREEN_HEIGHT / 2;

	public FileChooserScreen() {
		super();
		setUpResourceBundle(CHOOSER_RESOURCE);
		initialize(FC_BUTTONS);
		addButton();
	}

	/**
	 * Instantiates and adds the combo box to the screen
	 */
	private void addButton() {
		ComboBoxGame fileSelector = new ComboBoxGame(getResources().getString("Prompt"),
				getResources().getString("Directory"));
		fileSelector.addNodeObserver(this);
		HBox myBox = (HBox) fileSelector.createNode();
		myBox.setLayoutX(BOX_X - X_OFFSET);
		myBox.setLayoutY(BOX_Y);
		getPane().setCenter(myBox);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Displays an alert to the user if an error occurs which prevents the
	 * user's function from happening.
	 * 
	 * @param type
	 */
	public void alert(String type) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(getResources().getString(type));
		Optional<ButtonType> result = alert.showAndWait();
		if (type.equals("empty")) {
			if (result.get() == ButtonType.OK) {
				sendChange("goToSplash", null);
			}
		}

	}

}