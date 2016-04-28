package gameengine.controller;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.Triggers.ITrigger;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * This interface describes the API for a Level object Levels will contain sets
 * of Actor objects. When the game calls for a change in Levels, it will be able
 * to swap out scenes to get a different set of Actors to display.
 *
 * @author blakekaplan
 */
public interface ILevel extends IGameElement {

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
	
	/**
	 * Returns main character from game
	 * 
	 * @return Main character
	 */
	public IPlayActor getMainCharacter();
	
	public void scrollBackground(int change);
	
	public void removeActors(List<IPlayActor> deadActors);

	public String getMyScrollingDirection();

	public void setMyImageView(ImageView imageView);
	
	public DoubleProperty getMyBackgroundX();
	
	public String getMyBackgroundImgName();
	
}