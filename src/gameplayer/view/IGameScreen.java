package gameplayer.view;

import gameengine.model.IActor;
import javafx.event.Event;

/** 
 * This class serves as the private interface that a Game screen must implement in order to be able to add visual elements of the game to the screen.
 * It is the container for all the game contents that will be displayed on the screen.
 * @author cmt57
 */

public interface IGameScreen {
	
	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor (IActor actor);
	
	/**
	 * Will receive events on screen and then pass to the game engine's handler to determine what action to take
	 * @param e event 
	 */
	public void handleScreenEvent (Event e);
	
	/**
	 * Clears and reinitializes the visual elements (JavaFX nodes) of the GameScreen
	 */
	public void reset();
	
	
	/**
	 * Initializes the visual elements (JavaFX nodes) of the GameScreen
	 */
	public void setUp();
	
	/**
	 * Translates the Camera set on the subscene by the given x,y values
	 * @param x
	 * @param y
	 */
	public void changeCamera(int x, int y);
	
	/**
	 * Turns background music on the scene on or off.
	 * @param disable
	 */
	public void disableMusic(boolean disable);
	
	/**
	 * Turns all scene's actors' sound effects on or off.
	 * @param disable
	 */
	public void disableSoundFX(boolean disable);

}
