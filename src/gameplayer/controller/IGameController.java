package gameplayer.controller;

import gameengine.controller.Game;

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
	 * @return an instance of Game for XML serialization
	 */
	public Game getGame();

	/**
	 * Enables Game Authoring Environment to temporarily start the Game Engine
	 * to run a created game that it will have loaded into the Game Player for
	 * previewing purposes
	 */
	public void preview();

	/**
	 * Enables Game Authoring Environment and Game Data to start the Game Engine
	 * to run a game that either will have loaded into the Game Player
	 */
	public void play();

}
