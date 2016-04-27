package gamedata.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gamedata.view.FileChooserScreen;
import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.BranchScreenController;
import gameplayer.controller.GameController;
import gameplayer.controller.HighScoreScreenController;
import javafx.stage.Stage;

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

	private void setUpScreen() {
		this.myScreen = new FileChooserScreen();
		this.myScreen.addObserver(this);
		setMyScreen(this.myScreen);
	}

	private void goPlay(Game game) {
		BaseScreenController baseScreenController = new BaseScreenController(getStage(), new GameController(game));
	}

	private void goScores(Game game) {
		HighScoresController controller = new HighScoresController(game.getInitialGameFile(), myScreen);
		HighScoreScreenController highScoreScreenController = new HighScoreScreenController(getStage(), controller);
	}

	private void goEdit(Game game) {
		Controller GUIMainController = new Controller(game, getStage());
	}

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


	private void alert(String type) {
		try {
			Class[] parameterTypes = { String.class };
			Object[] parameters = { type };
			myScreen.getClass().getDeclaredMethod("alert", parameterTypes).invoke(myScreen, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean checkNullGame(Game game) {
		return game == null;
	}
	
	private String getType() {
		return myType.toString();
	}

	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) {
		try {
			this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}