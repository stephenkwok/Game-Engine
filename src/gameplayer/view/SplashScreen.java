package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
	 * initializes a hbox with buttons to facilitate transitions to the various
	 * parts of the program
	 * 
	 * @param stage
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public SplashScreen() {
		super();
		setUpResourceBundle(SPLASH_RESOURCE);
		initialize();
	}

	@Override
	protected void initialize() {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		try {
			setButtonsUp(hbox);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			showError(e.getMessage());
		}
		// TODO Magic constant
		hbox.setLayoutY(getScene().getHeight() / 6);
		BackgroundImage myBI= new BackgroundImage(new Image("hawaiianprint.jpg"),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		hbox.setBackground(new Background(myBI));		
		addToScene(hbox);
	}

	/**
	 * creates three buttons to change the scene to the user's choice of
	 * environment
	 * 
	 * @param hbox
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void setButtonsUp(HBox hbox)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] buttons = getResources().getString(BUTTONS_ID).split(",");
		for (int i = 0; i < buttons.length; i++) {
			IGUIElement newElement = getFactory().createNewGUIObject(buttons[i]);
			newElement.addNodeObserver(this);
			hbox.getChildren().add(newElement.createNode());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}


}