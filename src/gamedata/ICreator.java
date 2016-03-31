package gamedata;

import java.util.List;
import java.util.Map;

import authoringenvironment.model.IActor;

/** 
 * The purpose of this public interface is to provide an easy way for Authoring Environment to pass game information to save into an XML file.
 * @author cmt57
 *
 */

public interface ICreator {

	/**
	 * Saves all relevant information for each level in a specific format reflected at a basic level in initialGame.XML.
	 */
	public void saveLevel (Map<String, String> levelInfo, List<IActor> levelActors);

}
