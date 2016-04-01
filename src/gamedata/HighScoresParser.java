package gamedata;

import java.util.Map;

/**
 * The purpose of this class is to serve as the public interface for the HighScoresController, which will take information from an XML file to display high scores.
 * @author cmt57
 *
 */


public interface HighScoresParser {
	
	/**
	 * Will read through an XML file of the form highScores.XML and produce a map linking a game type to a map of that game's users linked to their high scores.
	 * @param filename a string name representing the file of high scores to read from
	 * @return a map of games to their high scores maps (linking user to score)
	 */
	public Map<String, Map<String, Integer>> getHighScoreInfo (String filename);

}
