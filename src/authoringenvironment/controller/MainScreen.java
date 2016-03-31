package authoringenvironment.controller;

import java.util.List;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;

/**
 * This class serves as the interface that all authoring environment main screens must implement
 * 
 * @author Stephen
 */

public interface MainScreen {

	/**
	 * Displays the Main Screen giving user option to create/edit actors and levels or save 
	 * data from created game into XML file 
	 */
	public void show();
	
	/**
	 * Switches screen to Level Editing Environment
	 * 
	 * @param level - level to be edited 
	 * @param createdActors - list of created Actors that can be placed into the level 
	 */
	public void goToLevelEditing(ICreatedLevel level, List<ICreatedActor> createdActors);
	
	/**
	 * Switches screen to Actor Editing Environment
	 * 
	 * @param actor - Actor to edit
	 */
	public void goToActorEditing(ICreatedActor actor);
	
	/**
	 * Passes Actor and Level info to Game Data to be saved in XML file
	 */
	public void saveGame();
	
}
