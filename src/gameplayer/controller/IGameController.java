package gameplayer.controller;

import gameengine.controller.Game;
import gameengine.controller.IGame;
import gameplayer.view.IGameScreen;

/**
 * This class serves as the public interface that any game controller must
 * implement in order to allow the game data, and game authoring environment to
 * interact with the game player and game engine for the purposes of loading and
 * running the game
 * 
 * @author cmt57
 */
public interface IGameController {

	/**
	 * Enables Game Data to pass a game (either deserialized from an XML file or
	 * created directly in the Game Authoring Environment) to load into the Game
	 * Player
	 * 
	 * @param game
	 *            an instance of Game created from XML deserialization
	 */
	public void setGame(Game game);

	/**
	 * Enables Game Data to obtain a game loaded into the Game Player for
	 * serialization in order to save game progress into an XML file
	 * 
	 * @return Game an instance of Game for XML serialization
	 */
	public IGame getGame();
	
	/**
	 * Allows the GameController to access the view of the Game to perform visual functionalities.
	 * @return GameScreen a view of the Game
	 */
	public IGameScreen getView();

	/**
	 * Restarts the game.
	 */
	public void restartGame();

	/**
	 * Unpauses the game timeline.
	 */
	public void toggleUnPause();

	/**
	 * Pauses the game timeline.
	 */
	public void togglePause();

	/**
	 * Turns the background music on or off.
	 */
	public void toggleMusic();

	/**
	 * Turns the sound effects on or off.
	 */
	public void toggleSound();


}
