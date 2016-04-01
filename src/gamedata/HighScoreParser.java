package gamedata;

import java.util.List;
import java.util.Map;

/** 
 * This class serves as the public interface that any Game Data HighScore Parser must implement in order to 
 * read data from an initial XML file and process it so that it can then be passed over in the right format
 * to the HighScoreScreen (which is looking for a map of players to their scores).
 * @author mdf15 Michael Figueiras
 */


public interface HighScoreParser {

	public Map<String, Map<String, Integer>> getHighScoreInfo(String fileName);
}
