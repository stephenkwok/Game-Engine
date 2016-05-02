package gameplayer.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gamedata.controller.HighScoresController;
import gameengine.controller.HighScoresKeeper;
import gameengine.controller.IHighScoresKeeper;
import gameplayer.view.HighScoreScreen;
import gameplayer.view.IHighScoreScreen;
import javafx.stage.Stage;

/**
 * This controller simply handles displaying the scores and user input like clearing or adding new entries. It also collaborates
 * with game data to work in conjunction with parsers and xstream to handles these changes. 
 * @author cmt57
 *
 */
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

	/**
	 * Calls the necessary methods to organize the front end scene
	 */
	private void setUpScreen() {
		this.myScreen = new HighScoreScreen();
		this.myScreen.displayScores(myGameName, myDataController.getGameHighScores());
		((Observable) this.myScreen).addObserver(this);
		setMyScreen(this.myScreen);
	}

	/**
	 * Ensures that the front end representation of the current scores matches the back end information
	 */
	private void updateScores() {
		myScreen.displayScores(myGameName, myScores.getGameScores(myGameName));
	}

	/**
	 * Returns to an intermediary controller which allows the user to choose a new game for a specific purpose
	 */
	private void switchGame() {
		FileChooserController fileChooserController = new FileChooserController(getStage(), ChooserType.SCORES);
	}

	/**
	 * Updates the model representation of a game's scores to reflect a purge in recorded information
	 */
	private void clearScores() {
		myScores.clearGameScores(myDataController.getGameFile());
		try {
			myDataController.clearHighScores();
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			this.myScreen.showError(e.getMessage());
		}
	}
	
	@Override
	public void invoke(String method, Class[] parameterTypes, Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			this.getClass().getDeclaredMethod(method, parameterTypes).invoke(this, parameters);
	}

}