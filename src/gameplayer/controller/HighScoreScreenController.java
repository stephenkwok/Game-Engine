package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gamedata.controller.HighScoresController;
import gameengine.controller.HighScoresKeeper;
import gameengine.controller.IHighScoresKeeper;
import gameplayer.view.HighScoreScreen;
import gameplayer.view.IHighScoreScreen;
import javafx.stage.Stage;

public class HighScoreScreenController extends BranchScreenController {

	private static final String SCORE_CONTROLLER_RESOURCE = "scoresActions";

	private IHighScoreScreen myScreen;
	private String myGameName;

	private IHighScoresKeeper myScores;
	private HighScoresController myDataController;

	public HighScoreScreenController(Stage myStage, HighScoresController dataController) {
		super(myStage,SCORE_CONTROLLER_RESOURCE);
		this.myDataController = dataController;
		this.myGameName = dataController.getGameFile();
		this.myScores = new HighScoresKeeper(this.myDataController.getAllGameScores());
		((Observable) this.myScores).addObserver(this);
		setUpScreen();
		changeScreen(myScreen);
	}

	private void setUpScreen() {
		this.myScreen = new HighScoreScreen();
		this.myScreen.displayScores(myGameName, myDataController.getGameHighScores());
		((Observable) this.myScreen).addObserver(this);
		setMyScreen(this.myScreen);
	}

	private void updateScores() {
		myScreen.displayScores(myGameName, myScores.getGameScores(myGameName));
	}

	private void switchGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), ChooserType.SCORES);
	}

	private void clearScores() {
		myScores.clearGameScores(myDataController.getGameFile());
		myDataController.clearHighScores();
	}
	
	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
	}

}