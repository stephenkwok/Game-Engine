package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gameplayer.view.HighScoreScreen;
import javafx.stage.Stage;

public class HighScoreScreenController extends BranchScreenController {

	private static final String SCORE_CONTROLLER_RESOURCE = "scoresActions";
	
	private ResourceBundle myResources;
	private HighScoreScreen myScreen;
	private Map<String, Integer> myModel;
	private String myGame;
	
	
	public HighScoreScreenController(Stage myStage, Map<String, Integer> scores, String game) {
		super(myStage);
		this.myModel = scores;
		this.myGame = game;
		setUpScreen(this.myModel, this.myGame);
		this.myResources = ResourceBundle.getBundle(SCORE_CONTROLLER_RESOURCE);
		changeScreen(myScreen);
	}

	private void setUpScreen(Map<String,Integer> model, String gameName) {
		this.myScreen = new HighScoreScreen(model, gameName);
		this.myScreen.addObserver(this);
	}
	private void switchGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), ChooserType.SCORES);
	}
	
	private void clearScores() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		String method = myResources.getString((String)arg);
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