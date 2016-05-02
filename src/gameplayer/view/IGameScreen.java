package gameplayer.view;

import gameengine.controller.Level;
import gameengine.model.IDisplayActor;
import javafx.event.Event;
import javafx.scene.Node;

/**
 * This class serves as the private interface that a Game screen must implement
 * in order to be able to add visual elements of the game to the screen. It is
 * the container for all the game contents that will be displayed on the screen.
 * 
 * @author cmt57
 */

public interface IGameScreen {

	/**
	 * Will add a node to the screen's scene representing the given actor's
	 * view.
	 * 
	 * @param actor
	 *            an instance of IActor
	 */
	public void addActor(IDisplayActor actor);

	/**
	 * Will receive events on screen and then pass to the game engine's handler
	 * to determine what action to take
	 * 
	 * @param e
	 *            event
	 */
	public void handleScreenEvent(Event e);

	/**
	 * Translates the Camera set on the subscene by the given x,y values
	 * 
	 * @param x
	 * @param y
	 */
	public void changeCamera(double x, double y);

	/**
	 * Turns background music on the scene on or off.
	 * 
	 * @param disable
	 */
	public void disableMusic(boolean disable);

	/**
	 * Turns all scene's actors' sound effects on or off.
	 * 
	 * @param disable
	 */
	public void disableSoundFX(boolean disable);

	/**
	 * Removes an actor from the game's scene's root
	 * @param IDisplayActor
	 */
	public void removeActor(IDisplayActor a);

	/**
	 * Repositions the camera and clears the screen of game nodes
	 */
	public void clearGame();

	/**
	 * Assigns the game's background image and relevant details to the saved properites taken from the game's xml file
	 * @param Level
	 */
	public void addBackground(Level current);

	/**
	 * Stops the game
	 */
	public void togglePause();

	/**
	 * Continues the game
	 */
	public void toggleUnPause();

	/**
	 * Clears current game to allow for a new game to be started
	 */
	public void restartGame();

	/**
	 * Returns the SubScene
	 */
	public Node getScene();

	/**
	 * Handles whether the user won or lost the game and alerts the user of this change
	 */
	public void terminateGame(boolean win);


}