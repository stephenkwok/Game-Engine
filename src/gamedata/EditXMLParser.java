package gamedata;

import java.util.List;
import java.util.Map;

import authoringenvironment.model.ICreatedActor;
import gameengine.controller.ILevel;

/** 
 * This class serves as the private interface that any Game Data xml parser reading game editing files must implement in order to read data from an initial XML file and process it so that it can then be passed over in the right format to the authoring environment (which is looking for a list of levels populated with info).
 * @author cmt57
 */

public interface EditXMLParser {
	
	/**
	 * Gets from an XML file all the information pertinent to a level's settings.
	 * @param infoTags a list of strings referring to tags in the XML storing level settings
	 * @return map associating information tag to information value (for all pieces of information contained in a level)
	 */
	public Map<String, String> getLevelInfo (List<String> infoTags);
	
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param actorTags a string of actors' information
	 * @return list of editqble actors
	 */
	public List<ICreatedActor> getLevelActors (String actorInfo);
	
	
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param levelInfo a list of actors 
	 * @return an instance of an editable level for the authoring environment
	 */
	
	public ILevel createLevel (Map<String, String> levelInfo, List<ICreatedActor> levelActors);
	
	/**
	 * Gets all levels enumerated in a saved XML file for editing purposes in the Authoring Environment.
	 * @return list of all levels associated to one game for editing
	 */
	public List<ILevel> getLevels();
	
}
