package gameengine.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public class HighScoresKeeper extends Observable {

	private Map<String, Map<String, Integer>> myScores;

	public HighScoresKeeper() {
		setMyScores(new HashMap<>());
	}

	public HighScoresKeeper(Map<String, Map<String, Integer>> scoresMap) {
		setMyScores(scoresMap);
	}

	public void clearGameScores(String gameIdentifier) {
		myScores.put(gameIdentifier, new TreeMap<>());
		setChanged();
		Object[] methodArg = {"updateScores", null};
		notifyObservers(Arrays.asList(methodArg));
	}

	public void addScore(String gameIdentifier, String player, int score) {
		Map<String, Integer> gameScores;
		if (getMyScores().keySet().contains(gameIdentifier)) {
			gameScores = (TreeMap<String, Integer>) getMyScores().get(gameIdentifier);
		} else {
			gameScores = new TreeMap<>();
		}

		if (gameScores.keySet().contains(player)) {
			if (gameScores.get(player) < score) {
				gameScores.put(player, score);
			}
		} else {
			gameScores.put(player, score);
		}

		getMyScores().put(gameIdentifier, gameScores);
	}

	public Map<String, Integer> getGameScores(String gameIdentifier) {
		return myScores.get(gameIdentifier);
	}

	/*
	 * public int getPlayerScores(Game gameIdentifier, String player) { return
	 * getGameScores(gameIdentifier).get(player); }
	 */

	public Map<String, Map<String, Integer>> getMyScores() {
		return myScores;
	}

	public void setMyScores(Map<String, Map<String, Integer>> myScores) {
		this.myScores = myScores;
	}

}