package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gamedata.controller.HighScoresController;
import gameengine.controller.HighScoresKeeper;
import gameplayer.view.HighScoreScreen;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.collections.MapChangeListener.Change;
import javafx.stage.Stage;

public class HighScoreScreenController extends BranchScreenController {

	private static final String SCORE_CONTROLLER_RESOURCE = "scoresActions";

	private ResourceBundle myResources;
	private HighScoreScreen myScreen;
	private String myGameName;

	private HighScoresKeeper myScores;
	private HighScoresController myDataController;

	public HighScoreScreenController(Stage myStage, HighScoresController dataController) {
		super(myStage);
		this.myDataController = dataController;
		this.myGameName = dataController.getGameFile();
		this.myScores = new HighScoresKeeper(this.myDataController.getAllGameScores());
		this.myScores.addObserver(this);
		setUpScreen();
		this.myResources = ResourceBundle.getBundle(SCORE_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}

	/*
	 * public HighScoreScreenController(Stage myStage, Map<String, Integer>
	 * scores, String game) { super(myStage); this.myModel =
	 * FXCollections.observableMap(scores); this.myModel.addListener(new
	 * MapChangeListener<String, Object>() {
	 * 
	 * @Override public void onChanged(Change<? extends String, ? extends
	 * Object> change) { if(change!=null && myScreen != null)
	 * myScreen.displayScores(myGameName, (Map<String, Integer>) change);; } });
	 * this.myGameName = game; setUpScreen(); this.myResources =
	 * ResourceBundle.getBundle(SCORE_CONTROLLER_RESOURCE);
	 * changeScreen(myScreen); }
	 */

	private void setUpScreen() {
		this.myScreen = new HighScoreScreen();
		this.myScreen.displayScores(myGameName, myDataController.getGameHighScores());
		this.myScreen.addObserver(this);
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
	public void update(Observable o, Object arg) {
		String method = myResources.getString((String) arg);
		try {
			try {
				this.getClass().getDeclaredMethod(method).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		} catch (NoSuchMethodException e) {
			try {
				this.getClass().getSuperclass().getDeclaredMethod(method).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e1) {
				e.printStackTrace();
				this.myScreen.showError(e.getMessage());
			}
		}

	}

}