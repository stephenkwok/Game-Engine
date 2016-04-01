package usecases;

import java.util.List;
import java.util.Map;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import gamedata.HighScoreParser;
import gamedata.XMLParser;

public class SampleParser implements HighScoreParser, XMLParser{

	public SampleParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, String> getLevelInfo(List<String> infoTags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICreatedActor> getLevelActors(String actorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICreatedLevel createLevel(Map<String, String> levelInfo, List<ICreatedActor> levelActors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<String>> getHighScoreInfo(List<String> gameTags) {
		// TODO Auto-generated method stub
		return null;
	}

}
