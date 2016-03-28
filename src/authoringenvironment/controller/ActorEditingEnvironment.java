package authoringenvironment.controller;

import authoringenvironment.model.IActor;

/**
 * This class serves as the interface that all actor editing environments must implement
 * 
 * @author Stephen
 */

public interface ActorEditingEnvironment {
	
	/**
	 * Initializes the Actor Editing Environment
	 */
	public void initializeEnvironment();
	
	/**
	 * Sets the environment's actor to be edited
	 * 
	 * @param actor to be edited 
	 */
	public void setActor(IActor actor);
	
}
