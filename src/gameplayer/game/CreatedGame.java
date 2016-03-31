package gameplayer.game;

import java.util.List;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import gameengine.model.IActor;

/** 
 * This class serves as the private interface that any Game must implement in order to set up the Game Engine with the appropriate levels and actors belonging to a game. 
 * @author cmt57
 */

public interface CreatedGame {
	
	
	/**
	 * Will populate the back end (game engine) with all the levels it will interact with based on what is received from the authoring environment or parser
	 * @param levels a list of editable levels
	 */
	public void setLevels (List<ICreatedLevel> levels);
	
	
	/**
	 * Will populate the back end (game engine) with an additional level it will interact with
	 * @param level an instance of an editable level
	 */
	public void addLevel (ICreatedLevel level);
	
	/**
	 * Will populate the back end (game engine) with all the actors of a level it will interact with based on what is received from the authoring environment or parser
	 * @param level an int representing the level from which to look at actors
	 */
	public void initializeCurrentActors (int level);
	
	/**
	 * Will create a back end (game engine) representation of an editable actor received from the authoring environment or parser
	 * @param actor an instance of an editable actor
	 * @return an instance of an IActor
	 */
	public IActor createActor (ICreatedActor actor);
	
	
	/**
	 * Will populate the back end (game engine) with an additional actor it will interact with
	 * @param actor an instance of an IActor
	 */
	public void addActor (IActor actor);

}
