package gameengine.controller;

import java.util.List;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IPlayActor;
import gameengine.model.Triggers.ITrigger;

/**
 * This interface describes the API for a Level object Levels will contain sets
 * of Actor objects. When the game calls for a change in Levels, it will be able
 * to swap out scenes to get a different set of Actors to display.
 *
 * @author blakekaplan
 */
public interface ILevel {

	/**
	 * Provided with a Trigger object, sets off the appropriate response
	 *
	 * @param myTrigger
	 *            A particular Trigger object sent from the game player
	 */
	public void handleTrigger(ITrigger myTrigger);

	/**
	 * Sets the Level's name
	 *
	 * @param name
	 *            A name for the Level
	 */
	public void setName(String name);

	/**
	 * Provides the Level's name
	 *
	 * @return The name of the Level object
	 */
	public String getName();

	/**
	 * Provides all of the Actors to be visualized by the player
	 *
	 * @return A List of all the Actors on in the Level
	 */
	public List<IPlayActor> getActors();

	/**
	 * Adds a new Actor to a Level
	 *
	 * @param newActor
	 *            The Actor to be added to the Level
	 */
	public void addActor(IAuthoringActor newActor);
}