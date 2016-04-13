package gameengine.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HighScoresKeeper {
	
	private Map<String, Map<String, Integer>> myScores;
	
	public HighScoresKeeper() {
		setMyScores(new HashMap<>());
	}
	
	public HighScoresKeeper(Map<String, Map<String,Integer>> scoresMap) {
		setMyScores(scoresMap);
	}
	
	
	public void addScore(String gameIdentifier, String player, int score) {
		Map<String, Integer> gameScores;
		if(getMyScores().keySet().contains(gameIdentifier)) {
			gameScores = (TreeMap<String, Integer>) getMyScores().get(gameIdentifier);
		}
		else {
			gameScores = new TreeMap<>();
		}
		
		if (gameScores.keySet().contains(player)) {
			if (gameScores.get(player) < score) {
				gameScores.put(player, score);
			}
		}
		else {
			gameScores.put(player, score);
		}
		
		getMyScores().put(gameIdentifier, gameScores);
	}
	
	public Map<String, Integer> getGameScores (Game gameIdentifier) {
		return getMyScores().get(gameIdentifier);
	}
	
	public int getPlayerScores(Game gameIdentifier, String player) {
		return getGameScores(gameIdentifier).get(player);
	}


	public Map<String, Map<String, Integer>> getMyScores() {
		return myScores;
	}


	public void setMyScores(Map<String, Map<String, Integer>> myScores) {
		this.myScores = myScores;
	}

	
	
}
