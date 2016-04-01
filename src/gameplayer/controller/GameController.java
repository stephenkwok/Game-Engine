package gameplayer.controller;

import java.util.List;

import gameengine.controller.IGame;
import gameengine.controller.ILevel;
import gameplayer.view.BaseScreen;

/** 
 * This class serves as the private interface that any game controller must implement in order to set up the IGame with the appropriate levels and actors for the game engine to interact with
 * @author cmt57
 */

public interface GameController {
	
	/**
	 * Will initialize the backend (game engine) with the current level's information and actor information to set up the game for playing.  Will visualize that backend too. 
	 * @param level an int representing the level to be played
	 */
	public IGame createGame (List<ILevel> myLevels);
	
	public BaseScreen createGameView (IGame myGame);
	
	public void setGame (IGame myGame);
	
	public void setGameView (BaseScreen myGameView);
	
	/**
	 * Will initialize the backend (game engine) with the current level's information and actor information to set up the game for playing.  Will visualize that backend too. 
	 * @param level an int representing the level to be played
	 */
	public void initialize (int level);
	
	/**
	 * Will play the animation timeline. 
	 */
	public void begin ();
	
	/**
	 * Will reflect changes in actors' positions or values in a new "step" to simulate one round of animation.
	 */
	public void update ();
	
	/**
	 * Will ask game engine to check interactions that need to be resolved.
	 */
	public void checkInteractions ();
	
	/**
	 * Will resolve any front end outcomes determined by logic in backend checking interactions.
	 */
	public void cleanUp ();
	
	/**
	 * Will stop the animation timeline.
	 */
	public void stop ();
	
	
	
	
	
	

}
