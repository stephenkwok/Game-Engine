package gamedata.controller;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

public interface IHighScoresController {
	
	public Map<String,Integer> getHighScores ();
	
	public void viewHighScores(Map<String,Integer> highScores);
	
	public void clearHighScores() throws SAXException, IOException;
	

}
