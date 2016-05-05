package gamedata.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gamedata.view.FileChooserScreen;
import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.BranchScreenController;
import gameplayer.controller.GameController;
import gameplayer.controller.HighScoreScreenController;
import javafx.stage.Stage;
/**
 * This class acts as an intermediary controller which sets the screen to a file chooser screen which allows the user 
 * to choose a game from a combo box and then transition to another part of the program with this selected game
 * @author cmt57
 *
 */
public class FileChooserController extends BranchScreenController {

	private static final int NODE = 0;
	private static final int METHOD_ARG = 1;
	private static final String CHOOSER_CONTROLLER_RESOURCE = "chooserActions";
	private static final String PROMPT = "GamePrompt";

	private ResourceBundle myResources;
	private FileChooserScreen myScreen;
	private ChooserType myType;

	public FileChooserController(Stage stage, ChooserType type) {
		super(stage, CHOOSER_CONTROLLER_RESOURCE);
		this.myType = type;
		setUpScreen();
		changeScreen(myScreen);
	}

	/**
	 * Initializes the front end view and adds this controller as an observer
	 */
	private void setUpScreen() {
		this.myScreen = new FileChooserScreen();
		this.myScreen.addObserver(this);
		setMyScreen(this.myScreen);
	}

	/**
	 * Transitions to a base screen controller and passes in the user-selected game to play
	 * @param game
	 */
	private void goPlay(Game game) {
		BaseScreenController baseScreenController = new BaseScreenController(getStage(), new GameController(game));
	}

	/**
	 * Transitions to a high score controller and passes in  the user-selected game to view scores
	 * @param game
	 */
	private void goScores(Game game) {
		HighScoresController controller = new HighScoresController(game.getInfo().getMyFile(), myScreen);
		HighScoreScreenController highScoreScreenController = new HighScoreScreenController(getStage(), controller);
	}

	/**
	 * Transition to the game authoring environment and passes in the user-selected game to edit
	 * @param game
	 */
	private void goEdit(Game game) {
		Controller GUIMainController = new Controller(game, getStage());
	}

	/**
	 * This method is used by reflection in other controllers and maps the specific user intention to which method should be invoked
	 * @param game
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private void go(Game game) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		if (game == null) {
			alert("choose");
		}
		else {
			Class[] parameterTypes = { Game.class };
			Object[] parameters = {game};
			this.getClass().getDeclaredMethod("go" + getType(), parameterTypes).invoke(this, parameters);
		}
	}


	/**
	 * Notifies the screen to display an alert if an error should occur
	 * @param type
	 */
	private void alert(String type) {
		try {
			Class[] parameterTypes = { String.class };
			Object[] parameters = { type };
			myScreen.getClass().getDeclaredMethod("alert", parameterTypes).invoke(myScreen, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			this.myScreen.showError(e.getMessage());
		}
	}
	
	/**
	 * Determines if the game is null
	 * @param game
	 * @return boolean
	 */
	private boolean checkNullGame(Game game) {
		return game == null;
	}
	
	/**
	 * Determines the current function of the controller, in other words, what the user intends to do with this gameg
	 * @return
	 */
	private String getType() {
		return myType.toString();
	}

	/**
	 * A method that executes a method of choice via reflection based on the provided arguments
	 */
	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);	
	}
	
}