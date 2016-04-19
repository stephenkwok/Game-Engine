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

	private static final int METHOD_NAME = 0;
	private static final int METHOD_ARG = 1;
	private static final String CHOOSER_CONTROLLER_RESOURCE = "chooserActions";
	
	private ResourceBundle myResources;
	private FileChooserScreen myScreen;
	private String myType;
	
	public FileChooserController(Stage stage, String type) {
		super(stage);
		this.myType = type;
		this.myScreen = new FileChooserScreen();
		this.myResources = ResourceBundle.getBundle(CHOOSER_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}
	
	private void goPlay(Game game) {
		BaseScreenController baseScreenController = new BaseScreenController(getStage(), new GameController(game));
	}
	
	private void goScores(Game game) {
		HighScoresController controller = new HighScoresController(game.getInfo().getMyFile(), myScreen);
		Map<String, Integer> gameScores = controller.getGameHighScores();
		HighScoreScreenController highScoreScreenController = new HighScoreScreenController(getStage(), gameScores, game.getInitialGameFile());
	}
	
	private void goEdit(Game game) {
		Controller GUIMainController = new Controller(getStage(), game);
	}


	@Override
	public void update(Observable o, Object arg) {
		try {
			if (o.getClass().equals(ButtonParent.class)) {
				handleButton((String) arg);
			}
			else {
				handleComboBox((List<Object>) arg);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			this.myScreen.showError(e.getMessage());
		}
	}
	
	private void handleButton(String buttonName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String method = myResources.getString(buttonName);
		this.getClass().getDeclaredMethod(method).invoke(this);
	}
	
	private void handleComboBox(List<Object> methodArgPair) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String methodName = (String) methodArgPair.get(METHOD_NAME);
		Game game = (Game) methodArgPair.get(METHOD_ARG);
		if(checkNullGame(game)) {
			//TODO implement resource bundle message
			this.myScreen.showError("Please choose game!");
		}
		else {
			this.getClass().getDeclaredMethod(methodName).invoke(this, game);
		}
	}
	
	private boolean checkNullGame(Game game) {
		return game == null;
	}
}
