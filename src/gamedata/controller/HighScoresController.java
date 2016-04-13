package gamedata.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gamedata.HighScoresCreator;
import gamedata.HighScoresParser;
import gameengine.controller.HighScoresKeeper;
import gameplayer.view.HighScoreScreen;
import gui.view.Screen;
import javafx.stage.Stage;

public class HighScoresController implements IHighScoresController {

	private static final String HIGH_SCORES_FILE = "resources/highScores.xml";
	
	private Stage myStage;
	private String myGameFile;
	private File myFile;
	private Screen myScreen;
	
	public HighScoresController(Stage stage, String gameFile, Screen screen) {
		this.myStage = stage;
		this.myGameFile = gameFile;
		myFile = new File(HIGH_SCORES_FILE);
		this.myScreen = screen;
	}
	
	private Map<String, Integer> getGameHighScores() {
		return getAllGameScores().get(myGameFile);
	}
	
	private Map<String, Map<String, Integer>> getAllGameScores()  {
		HighScoresParser scoresParser = new HighScoresParser();
		try {
			return scoresParser.getHighScoreInfo(myFile);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			this.myScreen.showError(e.getMessage());
			return null;
		}
	}

	@Override
	public void viewHighScores() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Map<String, Integer> gameHighScores = getGameHighScores();
		HighScoreScreen myHS = new HighScoreScreen(myStage);
		myStage.setScene(myHS.getMyScene());
	}

	@Override
	public void clearHighScores() throws SAXException, IOException, ParserConfigurationException, TransformerException {
		HighScoresKeeper newKeeper = new HighScoresKeeper();
		HighScoresCreator scoresCreator = new HighScoresCreator();
		scoresCreator.saveScore(newKeeper, myFile);
	}

	@Override
	public void saveHighScore(int score, String player) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		HighScoresKeeper updatedKeeper = new HighScoresKeeper(getAllGameScores());
		updatedKeeper.addScore(myGameFile, player, score);
		HighScoresCreator scoresCreator = new HighScoresCreator();
		scoresCreator.saveScore(updatedKeeper, myFile);
	}

}
