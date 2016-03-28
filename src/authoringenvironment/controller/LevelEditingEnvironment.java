package authoringenvironment.controller;

import authoringenvironment.model.ILevel;

/**
 * This class serves as the interface that all level editing environments must implement
 * 
 * @author Stephen
 */

public interface LevelEditingEnvironment {
	
	/**
	 * Initializes the Level Editing Environment
	 */
	public void initializeEnvironment();
	
	/**
	 * Sets the environment's level to be edited
	 * 
	 * @param level to be edited 
	 */
	public void setLevel(ILevel level);

}
