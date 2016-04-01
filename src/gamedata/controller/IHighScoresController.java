package gamedata.controller;

import java.util.Map;

public interface IHighScoresController {
	
	public Map<String,Integer> getHighScores ();
	
	public void viewHighScores(Map<String,Integer> highScores);
	

}
