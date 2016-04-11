package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import authoringenvironment.view.GUIMain;
import gameengine.controller.Game;
import gameplayer.controller.SplashScreenController;
import gui.controller.IScreenController;
import gui.view.ComboBoxGame;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class serves as the private interface that a Game must implement in
 * order to be able to provide functionality for the buttons that will be on the
 * splash screen first displayed to a user.
 * 
 * @author cmt57
 */

public class SplashScreen extends Screen {

	private ResourceBundle myResources;
	private SplashScreenController myController;
	private GUIFactory factory;
	private static final String GUI_RESOURCE = "splashGUI";
	private static final String BUTTONS_ID = "buttonID";
	private static final int PADDING = 200;
	private Stage myStage;

	public SplashScreen(Stage stage) {
		super(stage);
		this.myStage = stage;
		init();
	}

	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		setButtonsUp(hbox);
		getRoot().getChildren().add(hbox);
		hbox.setLayoutY(getMyScene().getHeight() / 6);
		return getMyScene();
	}

	public void setButtonsUp(HBox hbox)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] buttons = myResources.getString(BUTTONS_ID).split(",");
		for (int i = 0; i < buttons.length; i++) {
			IGUIElement newElement = factory.createNewGUIObject(buttons[i]);
			hbox.getChildren().add(newElement.createNode());
		}
		
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new SplashScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
		getStage().setTitle("Welcome to Vooga Salad!");
	}

	/**
	 * Will open a game playing screen.
	 * @param game TODO
	 */
	public void play(Game game) {
		BaseScreen myB = new BaseScreen(getStage(), game);
		getStage().setScene(myB.getMyScene());
	}

	/**
	 * Will open a authoring environment screen.
	 */
	public void edit() {
		GUIMain myGUI = new GUIMain(getStage(), getMyScene());
		getStage().setScene(myGUI.getScene());
	}

	/**
	 * Will open a high scores options screen.
	 */
	public void openHighScores() {
		HighScoreScreen myHS = new HighScoreScreen(getStage());
		getStage().setScene(myHS.getMyScene());
	}

	/**
	 * Will open a high scores screen corresponding to the chosen game to look
	 * at.
	 */
	public void openIndividualHighScores(String gameName) {
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}

}