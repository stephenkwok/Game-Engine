package gameplayer.view;

import java.util.Map;

import gui.view.IScreen;

/**
 * This class represents a private interface provides for a screen to display
 * the highest scores recorded for games.
 * 
 * @author Carine
 *
 */
public interface IHighScoreScreen extends IScreen {

	/**
	 * Creates the scene with with current scores saved in the game score xml
	 * data
	 */
	public void initialize();

	public void displayScores(String myGameName, Map<String, Integer> gameHighScores);


}
