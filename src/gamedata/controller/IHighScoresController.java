package gamedata.controller;

import java.util.Map;

/**
 * The purpose of this interface is to serve as the public interface with which the ISplashScreen can interact to deal with high scores functionality if the high scores button is pressed.
 * @author cmt57
 *
 */

public interface IHighScoresController {
	
	/**
	 * Will obtain the information for users mapped to their high scores for displaying.
	 * @return a map of user names associated with their high scores
	 */
	public Map<String,Integer> getHighScores ();
	
	/**
	 * Will display the high scores on a HighScoreScreen
	 * @param highScores a map of user names associated with their high scores
	 */
	public void viewHighScores(Map<String,Integer> highScores);
	

}
