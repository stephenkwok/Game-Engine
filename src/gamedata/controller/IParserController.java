package gamedata.controller;

import java.util.List;
import authoringenvironment.model.ICreatedLevel;
import gameengine.controller.ILevel;

/**
 * The purpose of this interface is to provide the loading capabilities from the authoring environment and game engine to play or edit a game from an XML file.
 * @author cmt57
 */

public interface IParserController {
	
	/**
	 * Uses the EditXMLParser to parse through an initial file to produce levels for editing
	 * @return a list of editable levels
	 * @param the name of the file to parse through
	 */
	public List<ICreatedLevel> loadForEditing (String filepath);
	
	/**
	 * Uses the GameXMLParser to parse through an game file to produce levels for playing
	 * @return a list of playable levels
	 * @param the name of the file to parse through
	 */
	public List<ILevel> loadforPlaying (String filepath);

}
