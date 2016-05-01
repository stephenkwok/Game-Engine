package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import voogasalad.util.hud.source.AbstractHUDScreen;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine, Michael
 *
 */
public class BaseScreen extends Screen implements Observer, IBaseScreen {

	private static final String BASE_RESOURCE = "gameGUI";
	private static final String SIDE_BUTTONS = "SideButtons";

	private BorderPane myPane;
	private IGameScreen myGameScreen;
	private AbstractHUDScreen hud;

	/**
	 * Adds the auxiliary views, like the HUD display, ToolBar, and GameScreen,
	 * to the BaseScreen
	 * 
	 * @param stage
	 *            to change the scene
	 * @param game
	 *            to initialize the gamescreen with
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public BaseScreen() {
		super();
		this.myPane = new BorderPane();
		setUpResourceBundle(BASE_RESOURCE);
		initialize();
	}

	public void setGameScreen(IGameScreen iGameScreen) {
		this.myGameScreen = iGameScreen;
		this.myPane.setCenter(myGameScreen.getScene());
	}

	public void setHUDScreen(AbstractHUDScreen screen) {
		this.hud = screen;
		IGUIElement hudPane = getFactory().createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		myP.getChildren().add(hud.getScene());
		this.myPane.setBottom(myP);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Displays the proper alert sequences for ending and saving games
	 */
	public void switchAlert() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, getResources().getString("SwitchConfirmation"),
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			setChanged();
			Object[] methodArg = { "saveGame", null };
			notifyObservers(Arrays.asList(methodArg));
			setChanged();
			Object[] methodArg2 = { "chooseGame", null };
			notifyObservers(Arrays.asList(methodArg2));
		} else if (result.get() == ButtonType.NO) {
			setChanged();
			Object[] methodArg = { "chooseGame", null };
			notifyObservers(Arrays.asList(methodArg));
		} else {
			setChanged();
			Object[] methodArg = { "toggleUnPause", null };
			notifyObservers(Arrays.asList(methodArg));
		}

	}

	@Override
	protected void initialize() {
		myPane.setTop(addToolbar(SIDE_BUTTONS));
		getRoot().getChildren().add(myPane);

	}
}