//This entire file is part of my masterpiece.
// Michael Figueiras, The Loops Goat Cheese Salad
/**
 * See BaseScreen.java in the gameplayer.screen package for a detailed explanation of how the refactoring of the 
 * Screen class is implemented in these examples.
 */

package gameplayer.view;

import java.util.Observable;
import java.util.Observer;

import gui.view.Screen;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * This class serves as the private interface that a Game must implement in
 * order to be able to provide functionality for the buttons that will be on the
 * splash screen first displayed to a user.
 * 
 * @author cmt57, mdf15
 */

public class SplashScreen extends Screen implements Observer, ISplashScreen {

	private static final String SPLASH_RESOURCE = "splashGUI";
	private static final String BUTTONS_ID = "buttonID";
	private static final int PADDING = 190;

	/**
	 * Initializes a starter screen with buttons to facilitate transitions to the various
	 * parts of the program
	 */
	public SplashScreen() {
		super();
		setUpResourceBundle(SPLASH_RESOURCE);
		initialize(BUTTONS_ID);
		setUp();
	}

	protected void setUp() {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		hbox.setLayoutY(getScene().getHeight() / 6);
		BackgroundImage myBI = new BackgroundImage(new Image("hawaiianprint.jpg"), BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		hbox.setBackground(new Background(myBI));
		super.getPane().setCenter(hbox);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

}