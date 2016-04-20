package authoringenvironment.model;

import authoringenvironment.view.ActorRule;
import gameengine.model.IRule;
import gameengine.model.Rule;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Set;

/**
 * This interface defines the subset of Actor functionality that will be accessible to the game authoring environment.
 * We decided to make a separate interface since the authoring environment has different functionality requirements.
 *
 * @author blakekaplan
 */
public interface IAuthoringActor extends IEditableGameElement{
	/**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
	void addRule(Rule newRule);
	/**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    Set<String> getTriggers();
    /**
     * Sets the Actor's X coordinate
     * @param updateXPosition   The new X coordinate
     */
    void setX(double updateXPosition);
    /**
     * Sets the Actor's Y position
     * @param updateYPosition   The new Y position
     */
    void setY(double updateYPosition);
    /**
     * Provides the Actor's name
     * @return  The Actor's name
     */
    String getMyName();
    /**
     * Sets a new Actor name
     * @param name  The new Actor name
     */
    void setMyName(String name);
    /**
     * Provides the Actor's Imageview
     * @return  The Actor's Imageview
     */
    ImageView getMyImageView();
    /**
     * Sets a new Actor ImageView
     * @param imageView The new ImageView
     */
    void setMyImageView(ImageView imageView);
    /**
     * Provides the Actor's X coordinate
     * @return  The Actor's X coordinate
     */
    double getX();
    /**
     * Provides the Actor's Y coordinate
     * @return  The Actor's Y coordinate
     */
    double getY();
    /**
     * Provides the Actor's ImageView
     * @return  The Actor's ImageView
     */
    String getMyImageViewName();
    /**
     * Sets the name of the Actor's ImageView
     * @param myImageViewName   The Actor's ImageView
     */
    void setMyImageViewName(String imageViewName);
    /**
     * Sets the Actor's ImageView's size
     * @param size  The ImageView's size
     */
    void setSize(double size);
    /**
     * Sets the Actor's ImageView's size
     * @param size  The ImageView's size
     */
	double getSize();
    /**
     * Adds a new ActorRule
     * @param actorRule The new ActorRule
     */
    void addActorRule(ActorRule actorRule);
    /**
     * Removes an Actor Rule
     * @param actorRule The Rule to be removed
     */
    void removeActorRule(ActorRule actorRule);
    /**
     * Provides the List of the Actor's Rules
     * @return  The Actor's ActorRules
     */
    List<ActorRule> getActorRules();
    /**
	 * Return whether the actor is a playable, main character.
	 * @return
	 */
	boolean isMain();
	/**
	 * Set whether this actor is a playable, main character.
	 */
	void setMain(boolean parseBoolean);
	/**
	 * @param myFriction the myFriction to set
	 */
	void setMyFriction(double parseDouble);
	
	/**
	 * @return the myFriction
	 */
	double getMyFriction();
	/**
     * Sets a new Actor ID
     * @param ID    The new ID
     */
    void setMyID(int id);
    /**
     * Provides the Actor's ID number
     *
     * @return The Actor's ID number
     */
    int getMyID();
}
