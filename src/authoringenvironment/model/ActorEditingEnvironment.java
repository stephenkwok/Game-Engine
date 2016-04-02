package authoringenvironment.model;

import gameengine.model.IActor;
import javafx.scene.Scene;

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
	
	/**
	 * 
	 * @return Editing Environment's Scene
	 */
	public Scene getScene();
	
}
