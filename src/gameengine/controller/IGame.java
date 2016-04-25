package gameengine.controller;

import gameengine.model.IActor;
import gameengine.model.Triggers.ITrigger;

import java.util.List;

/**
 * This interface defines the API for the Game class. The Game will be the main
 * point of interaction between the Game Player and the Game Engine. It will
 * allow player to switch Levels and to get all of Actors to display on the
 * screen.
 *
 * @author blakekaplan
 */
public interface IGame {

	/**
	 * Provides a list of all the possible levels to be played
	 *
	 * @return A list of Level objects that can be played
	 */
	public List<Level> getLevels();

	/**
	 * Provided with a Trigger object from the player, sets off the appropriate
	 * response
	 *
	 * @param myTrigger
	 *            A Trigger object provided by the game player
	 */
	public void handleTrigger(ITrigger myTrigger);

}
