package gamedata.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * The purpose of this interface is to serve as the public interface with which
 * the ISplashScreen can interact to deal with high scores functionality if the
 * high scores button is pressed.
 * 
 * @author cmt57
 *
 */
public interface IHighScoresController {

	/**
	 * Enables Game Player to obtain non game-specific (every game's) high
	 * scores for displaying purposes on a score board
	 * 
	 * @return Map<String, Map<String, Integer>> maps a game's identifier (name)
	 *         to a map of that game's high scores information per player
	 */
	public Map<String, Map<String, Integer>> getAllGameScores();

	/**
	 * Enables Game Player to obtain game-specific high scores for displaying
	 * purposes on a score board
	 * 
	 * @return Map<String, Integer> that maps all of a game's players to their
	 *         high scores
	 */
	public Map<String, Integer> getGameHighScores();

	/**
	 * Enables the Game Player to pass Game Data a high score (and associated
	 * player) to be recorded
	 * 
	 * @param score
	 * @param player
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public void saveHighScore(List<Integer> score, List<String> player)
			throws ParserConfigurationException, SAXException, IOException, TransformerException;

	/**
	 * Will clear the high scores from a highScores.xml
	 * 
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	public void clearHighScores() throws SAXException, IOException, ParserConfigurationException, TransformerException;


}
