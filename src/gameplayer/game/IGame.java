package gameplayer.game;

/** 
 * This interface serves as the public interface that any Game must implement in order to allow the authoring environment and game engine the capabilities of playing (or previewing) and terminating a created game. 
 * @author cmt57
 */

public interface IGame {
	
	
	/**
	 * Will play the game from the beginning (first level).
	 */
	public void startGame ();
	
	/**
	 * Will play the game from a particular level. 
	 * @param level an int representing the level to be played
	 */
	public void playAt (int level);
	
	/**
	 * Will stop the game. 
	 */
	public void endGame ();

}
