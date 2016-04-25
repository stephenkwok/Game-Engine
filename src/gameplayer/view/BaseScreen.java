package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine, Michael
 *
 */
public class BaseScreen extends Screen implements Observer {

	private static final String BASE_RESOURCE = "gameGUI";
	private static final String SIDE_BUTTONS = "SideButtons";

	private BorderPane myPane;
	private HUDScreen myHUD;
	private GameScreen myGameScreen;

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
		initialize(); // HUD is actually added here
	}

	@Override
	protected void initialize() {
		myPane.setTop(addToolbar(SIDE_BUTTONS));
		addHUD();
		getRoot().getChildren().add(myPane);
	}

	// depracated
	private void addHUD() {

		notifyObservers("addHUD");
		/*
		 * ObservableMap<String, Object> status =
		 * FXCollections.observableHashMap(); status.put("health", 20);
		 * status.put("level", 2); HUDScreen myHud = new
		 * HUDScreen(SCREEN_WIDTH,SCREEN_WIDTH,status);
		 */
		/*
		 * HUDScreen myHud = new HUDScreen(SCREEN_WIDTH, SCREEN_WIDTH,
		 * myBaseScreenController.getMyGameController().getGame().getHUDData());
		 * myBaseScreenController.getMyGameController().setHUD(myHud);
		 * myHud.init(); myP.getChildren().add(myHud.getScene());
		 * myMasterPane.setBottom(myP); //myMasterPane.setBottom(new
		 * Text("HELLO!!!!"));
		 */
	}

	public void setGameScreen(GameScreen screen) {
		this.myGameScreen = screen;
		this.myPane.setCenter(myGameScreen.getScene());
	}

	public void setHUDScreen(HUDScreen screen) {
		this.myHUD = screen;
		IGUIElement hudPane = getFactory().createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		myP.getChildren().add(myHUD.getScene());
		this.myPane.setBottom(myP);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(o.getClass().getSimpleName());
	}

	public void switchAlert() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, getResources().getString("SwitchConfirmation"),
				ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			setChanged();
			notifyObservers("ButtonSaveGame");
			setChanged();
			notifyObservers("choose");
		} else if (result.get() == ButtonType.NO) {
			setChanged();
			notifyObservers("choose");
		} else {
			setChanged();
			notifyObservers("ButtonUnPause");
		}

	}

}