package gamedata;

import java.util.List;
import java.util.Map;

import gameengine.controller.ILevel;
import gameengine.model.IActor;

/** 
 * This class serves as the private interface that any Game Data xml parser reading playable game files must implement in order to read data from an initial XML file and process it so that it can then be passed over in the right format to the game engine (which is looking for a list of levels populated with info).
 * @author cmt57
 */

public interface GameXMLParser {
	
	/**
	 * Gets from an XML file all the information pertinent to the current level's settings.
	 * @param infoTags a list of strings referring to tags in the XML storing level settings
	 * @return map associating information tag to information value (for all pieces of information contained in a level)
	 */
	public Map<String, String> getLevelInfo (List<String> infoTags);
	
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param actorTags a string of actors' information
	 * @return list of current level's actors
	 */
	public List<IActor> getLevelActors (String actorInfo);
		
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param levelInfo a list of actors 
	 * @return an instance of a playable level for the game engine
	 */
	public ILevel createLevel (Map<String, String> levelInfo, List<IActor> levelActors);
	
	/**
	 * Gets all levels enumerated in a saved XML file belonging to a playable game.
	 * @return list of all levels associated to one game for playing
	 */
	public List<ILevel> getLevels ();


}
