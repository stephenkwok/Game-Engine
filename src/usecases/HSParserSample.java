package usecases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.HighScoreParser;

public class HSParserSample implements HighScoreParser {

	public HSParserSample() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, List<String>> getHighScoreInfo(List<String> gameTags) {
		Map<String, List<String>> myMap = new HashMap<String,List<String>>();
		for(String item : gameTags){
			
		}
		return null;
	}

}
