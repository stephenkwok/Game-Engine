package gameplayer.controller;

import java.util.List;
import gameengine.controller.ILevel;
import gameengine.model.IActor;

/** 
 * This class serves as the private interface that any game controller must implement in order to set up the IGame with the appropriate levels and actors for the game engine to interact with
 * @author cmt57
 */

public interface GameController {
	
	/**
	 * Will populate the back end IGame with all the levels taken from data or the authoring environment
	 * @param levels a list of playable levels
	 */
	public void setLevels (List<ILevel> levels);
	
	/**
	 * Will populate the back end IGame with an new level
	 * @param level an instance of a playable level
	 */
	public void addLevel (ILevel level);
	
	/**
	 * Will populate the back end IGame with all the actors of a level it will interact with based on what is received from the authoring environment or data
	 * @param level an int representing the level from which to look at actors
	 */
	public void initializeCurrentActors (int level);
	
	/**
	 * Will populate the back end IGame with an additional actor it will interact with
	 * @param actor an instance of an IActor
	 */
	public void addActor (IActor actor);

}
