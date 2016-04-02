package authoringenvironment.model;

import java.util.List;

import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.scene.layout.Pane;

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
	
	/**
	 * 
	 * @return Editing Environment's Layout
	 */
	public Pane getPane();

}
