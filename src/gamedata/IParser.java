package gamedata;

import java.util.List;

import authoringenvironment.model.ILevel;

/** 
 * The purpose of this interface is to provide an easy way to pass game information (level by level) to the Authoring environment from a saved XML file.
 * @author cmt57
 */

public interface IParser {
	
	/**
	 * Gets all levels enumerated in a saved XML file for editing purposes in the Authoring Environment.
	 * @return list of all levels associated to one game for editing
	 */
	public List<ILevel> getLevels();

}
