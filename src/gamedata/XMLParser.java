package gamedata;

import java.util.List;
import java.util.Map;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;

/** 
 * This class serves as the private interface that any Game Data XMLParser must implement in order to read data from an initial XML file and process it so that it can then be passed over in the right format to the authoring environment (which is looking for a list of levels populated with info).
 * @author cmt57
 */

public interface XMLParser {
	
	public Map<String, String> getLevelInfo (List<String> infoTags);
	
	public List<ICreatedActor> getLevelActors (List<String> actorTags);
	
	public ICreatedLevel createLevel (Map<String, String> levelInfo, List<ICreatedActor> levelActors);
	
	
}
