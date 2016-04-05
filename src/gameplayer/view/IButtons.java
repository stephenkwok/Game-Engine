package gameplayer.view;

/** 
 * This class serves as the private interface that a Game must implement in order to be able to provide functionality for the buttons that will be on the screen.
 * @author cmt57
 */

public interface IButtons {
	
	/**
	 * Will restart game loop.
	 */
	public void replayGame ();
	
	/**
	 * Will replay game at current level.
	 */
	public void replayLevel ();
	
}
