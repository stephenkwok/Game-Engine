package gamedata.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.controller.Controller;
import gamedata.view.FileChooserScreen;
import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.BranchScreenController;
import gameplayer.controller.GameController;
import gameplayer.controller.HighScoreScreenController;
import gui.view.ButtonParent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class FileChooserController extends BranchScreenController {

	private static final int NODE = 0;
	private static final int METHOD_ARG = 1;
	private static final String CHOOSER_CONTROLLER_RESOURCE = "chooserActions";
	private static final String PROMPT = "GamePrompt";
	@XStreamOmitField
	private ResourceBundle myResources;
	private FileChooserScreen myScreen;
	@XStreamOmitField
	private ChooserType myType;

	public FileChooserController(Stage stage, ChooserType type) {
		super(stage);
		this.myType = type;
		setUpScreen();
		this.myResources = ResourceBundle.getBundle(CHOOSER_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}

	private void setUpScreen() {
		this.myScreen = new FileChooserScreen();
		this.myScreen.addObserver(this);
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
			this.getClass().getDeclaredMethod("go" + myType.toString(), parameterTypes).invoke(this, game);
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

	@Override
	public void update(Observable o, Object arg) {
		List<Object> myList = (List<Object>) arg;
		String methodName = (String) myList.get(0);
		try {
			if (myResources.getString(methodName).equals("null")) {
				this.getClass().getDeclaredMethod(methodName).invoke(this);
			} 
			else if (myResources.getString(methodName).equals("String")) {
				Class[] parameterTypes = {String.class};
				Object[] parameters = {(String) myList.get(1)};
				this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, parameters);
			} 
			else {
				Class<?> myClass = Class.forName(myResources.getString(methodName));
				Object arg2 = myClass.cast(myList.get(1));
				Class[] parameterTypes = { myClass };
				Object[] parameters = { arg2 };
				System.out.println(methodName);
				this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, parameters);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			try {
				this.getClass().getSuperclass().getDeclaredMethod(methodName).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		}
	}
}
