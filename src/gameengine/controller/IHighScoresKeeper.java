package gameengine.controller;

import java.util.Map;

public interface IHighScoresKeeper {

	Map<String, Integer> getGameScores(String myGameName);

	void clearGameScores(String gameFile);

}
