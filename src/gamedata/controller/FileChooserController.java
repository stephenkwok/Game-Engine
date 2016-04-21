package gamedata.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gamedata.view.FileChooserScreen;
import gameengine.controller.Game;
import gameplayer.controller.BaseScreenController;
import gameplayer.controller.BranchScreenController;
import gameplayer.controller.GameController;
import gameplayer.controller.HighScoreScreenController;
import gui.view.ButtonParent;
import gui.view.Screen;
import javafx.stage.Stage;

public class FileChooserController extends BranchScreenController {

	private static final int NODE = 0;
	private static final int METHOD_ARG = 1;
	private static final String CHOOSER_CONTROLLER_RESOURCE = "chooserActions";
	
	private ResourceBundle myResources;
	private FileChooserScreen myScreen;
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
		//Controller GUIMainController = new Controller(getStage(), game);
	}
	
	private void handleButton(String buttonName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		String method = myResources.getString(buttonName);
		try {
			this.getClass().getDeclaredMethod(method).invoke(this);
		} catch (NoSuchMethodException e) {
			this.getClass().getSuperclass().getDeclaredMethod(method).invoke(this);
		}
	}
	
	private void handleComboBox(List<Object> methodArgPair) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String methodName = myResources.getString((String) methodArgPair.get(NODE).getClass().getSimpleName()) + this.myType.toString();
		Game game = (Game) methodArgPair.get(METHOD_ARG);
		if(checkNullGame(game)) {
			//TODO implement resource bundle message
			this.myScreen.showError("Please choose game!");
		}
		else {
			Class[] parameterTypes = {Game.class};
			Object[] parameters = {game};
			this.getClass().getDeclaredMethod(methodName, parameterTypes).invoke(this, parameters);
		}
	}
	
	private boolean checkNullGame(Game game) {
		return game == null;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		List<Object> nodeArg = (List<Object>) arg;
		try {
			if (nodeArg.get(NODE) instanceof ButtonParent) {
				handleButton(nodeArg.get(NODE).getClass().getSimpleName());
			}
			else {
				handleComboBox(nodeArg);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			this.myScreen.showError(e.getMessage());
		}
	}
}