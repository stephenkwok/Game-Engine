package gameplayer.controller;

/** 
 * This class serves as the private interface that a game loop must implement in order to be able to animate the changes determined by the game engine.
 * @author cmt57
 */

public interface GameLoop {
	
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
