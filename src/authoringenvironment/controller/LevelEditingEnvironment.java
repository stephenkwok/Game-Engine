package authoringenvironment.controller;

import java.util.List;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import javafx.scene.Scene;

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
	public void setLevel(ICreatedLevel level, List<ICreatedActor> actors);
	
	/**
	 * 
	 * @return Editing Environment's Scene
	 */
	public Scene getScene();

}
