package gamedata;

import java.util.List;
import java.util.Map;

import authoringenvironment.model.ICreatedActor;

/** 
 * The purpose of this public interface is to provide an easy way for Authoring Environment to pass game information to save into an XML file.
 * @author cmt57
 *
 */

public interface ICreator {

	/**
	 * Saves all relevant information for each level in a specific format reflected at a basic level in initialGame.XML.
	 * @param levelInfo a map of tags referring to level settings matched to their values
	 * @param levelActors a list of actors belonging to a level's editing environment
	 */
	public void saveLevel (Map<String, String> levelInfo, List<ICreatedActor> levelActors);

}
