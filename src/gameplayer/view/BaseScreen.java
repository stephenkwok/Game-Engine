//This entire file is part of my masterpiece.
// Michael Figueiras, The Loops Goat Cheese Salad
/*
 * This class is intended to display the main screen of our program, where games are played (and subsequent HUD data visually updated)
 * and high level program operations (like switching the game or exiting to the splash screen) take place. It serves as an example
 * implementation of the superclass Screen in the gui.view package. 
 * 
 * Good design principles are evident here in the reuse of parent methods like notifying controllers of user input changes and also
 * in modeling the "is-a" relationship (i.e. BaseScreen, as well as all other screens, is-a Screen) between implementation and inheritance. In terms of code change ripple effects, the consequences 
 * of edits to the superclass are minimized since the relationship between these two classes will always exist in an inheritance fashion,
 * meaning that changes to one will always need to take effect in the other (in other words, a BaseScreen will always need to
 * do the work of a Screen). This reinforces open-closed extension capabilities while maintaining the flexibility 
 * of this class to employ its superclass's methods.
 * 
 * See Screen in the gui.view package to see how this is an extension of the superclass.
 */
package gameplayer.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import voogasalad.util.hud.source.AbstractHUDScreen;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author cmt57, mdf15
 *
 */
public class BaseScreen extends Screen implements Observer, IBaseScreen {

	private static final String BASE_RESOURCE = "gameGUI";
	private static final String SIDE_BUTTONS = "SideButtons";
	private IGameScreen myGameScreen;
	private AbstractHUDScreen hud;

	/**
	 * Adds the auxiliary views, like the HUD display, ToolBar, and GameScreen,
	 * to the BaseScreen
	 */
	public BaseScreen() {
		super();
		setUpResourceBundle(BASE_RESOURCE);
		initialize(SIDE_BUTTONS);
	}

	public void setGameScreen(IGameScreen iGameScreen) {
		this.myGameScreen = iGameScreen;
		super.getPane().setCenter(myGameScreen.getScene());
	}

	public void setHUDScreen(AbstractHUDScreen screen) {
		this.hud = screen;
		IGUIElement hudPane = super.getFactory().createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		myP.getChildren().add(hud.getScene());
		super.getPane().setBottom(myP);
	}

	/**
	 * JavaFX Observer method
	 */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Prompts the user for the appropriate controller decisions, like whether
	 * to return to splash or restart, based on how the game ended
	 */
	public void switchAlert() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, super.getResources().getString("SwitchConfirmation"),
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			sendChange("saveGame", null);
			sendChange("chooseGame", null);
		} else if (result.get() == ButtonType.NO) {
			sendChange("chooseGame", null);
		} else {
			sendChange("toggleUnPause", null);
		}

	}
}