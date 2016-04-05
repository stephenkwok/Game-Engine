package gamedata.controller;

import java.util.List;

/**
 * The purpose of this interface is to provide the saving capabilities for the authoring environment and game engine to save a game being played or edit to an XML file.
 * @author cmt57
 */

public interface ICreatorController {
	
	/**
	 * Uses the EditXMLCreator to write to an initial file the details for levels that can be edited again by the authoring environment
	 * @return a list of editable levels
	 * @param the name of the file to save to
	 */
	public void saveForEditing (String filepath);
	
	/**
	 * Uses the GameXMLCreator to write to an initial file the details for a current game being played so it can be reloaded again for the game engine
	 * @return a list of playable levels
	 * @param the name of the file to save to
	 */
	public void saveforPlaying (String filepath);

}
