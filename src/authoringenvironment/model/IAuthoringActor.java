package authoringenvironment.model;

import java.util.List;
import java.util.Map;

import gameengine.model.ActorState;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.PhysicsEngine;
import gameengine.model.Rule;
import gameengine.model.Sprite;
import javafx.scene.image.ImageView;

/**
 * This interface defines the subset of Actor functionality that will be
 * accessible to the game authoring environment. We decided to make a separate
 * interface since the authoring environment has different functionality
 * requirements.
 *
 * @author blakekaplan
 */
public interface IAuthoringActor extends IEditableGameElement {
	/**
	 * Adds a new Rule to the Actor
	 *
	 * @param newRule
	 *            The Rule to be added to the Actor
	 */
	void addRule(Rule newRule);

	/**
	 * Gets Map<String, List<Rule>>
	 * 
	 * @return
	 */
	public Map<String, List<Rule>> getRules();

	/**
	 * Removes given Rule from Actor
	 * 
	 * @return
	 */
	public void removeRule(Rule rule);

	/**
	 * Sets the Actor's X coordinate
	 * 
	 * @param updateXPosition
	 *            The new X coordinate
	 */
	void setX(double updateXPosition);

	/**
	 * Sets the Actor's Y position
	 * 
	 * @param updateYPosition
	 *            The new Y position
	 */
	void setY(double updateYPosition);

	/**
	 * Provides the Actor's name
	 * 
	 * @return The Actor's name
	 */
	String getName();

	/**
	 * Sets a new Actor name
	 * 
	 * @param name
	 *            The new Actor name
	 */
	void setName(String name);

	/**
	 * Provides the Actor's Imageview
	 * 
	 * @return The Actor's Imageview
	 */
	ImageView getImageView();

	/**
	 * Sets a new Actor ImageView
	 * 
	 * @param imageView
	 *            The new ImageView
	 */
	void setImageView(ImageView imageView);

	/**
	 * Provides the Actor's X coordinate
	 * 
	 * @return The Actor's X coordinate
	 */
	double getX();

	/**
	 * Provides the Actor's Y coordinate
	 * 
	 * @return The Actor's Y coordinate
	 */
	double getY();

	/**
	 * Provides the Actor's ImageView
	 * 
	 * @return The Actor's ImageView
	 */
	String getImageViewName();

	/**
	 * Sets the name of the Actor's ImageView
	 * 
	 * @param imageViewName
	 *            The Actor's ImageView
	 */
	void setImageViewName(String imageViewName);

	/**
	 * Sets the Actor's ImageView's size
	 * 
	 * @param size
	 *            The ImageView's size
	 */
	void setSize(double size);

	/**
	 * Sets the friction value.
	 * @param parseDouble: friction value.
	 */
	void setFriction(double parseDouble);

	/**
	 * @return the myFriction
	 */
	double getFriction();

	/**
	 * Get the actor's ID.
	 * @return ID.
	 */
	int getID();

	/**
	 * Add a state to an actor.
	 * @param state: state to add.
	 */
	void addState(ActorState state);

	/**
	 * Check state of actor.
	 * @param main: main state.
	 * @return true if main actor; false o.w.
	 */
	boolean checkState(ActorState main);

	/**
	 * Get actor's size.
	 * @return actor's size.
	 */
	double getSize();

	/**
	 * Sets the ID of the Actor
	 * @param ID: the ID to be assigned to the Actor
	 */
	void setID(int ID);

	/**
	 * Add an attribute to an actor.
	 * @param newAttribute: attribute to add.
	 */
	void addAttribute(Attribute newAttribute);

	/**
	 * Add a sprite image.
	 * @param newImage: attribute to add.
	 */
    void addSpriteImage(String newImage);
    
    /**
     * Get the sprite.
     * @return: sprite.
     */
    public Sprite getSprite();
    
    /**
     * Sets the Actor's rotate property
     * @param rotate: the value the Actor's rotate property is to be set to
     */
    public void setRotate(double rotate);
    
    /**
     * 
     * @return: the value of the Actor's rotate property
     */
    public double getRotate();
    
    /**
     * Sets the Actor's opacity property
     * @param opacity: the value the Actor's opacity property is to be set to
     */
    public void setOpacity(double opacity);
    
    /**
     * 
     * @return: the value of the Actor's opacity property
     */
    public double getOpacity();
    
    /**
     * Sets the Actor's scaleX property
     * @param scaleX: the value the Actor's scaleX property is to be set to
     */
    public void setScaleX(double scaleX);
    
    /**
     * 
     * @return: the value of the Actor's scaleX property
     */
    public double getScaleX();
    
    /**
     * Sets the Actor's scaleY property
     * @param scaleY: the value the Actor's scaleY property is to be set to
     */
    public void setScaleY(double scaleY);
    
    /**
     * @return: the value of the Actor's scaleY property
     */
    public double getScaleY();
    
    /**
     * Restores the Actor's ImageView according to its
     * size, opacity, rotate, scaleX, and scaleY properties
     */
    public void restoreImageView();

    /**
     * Get an actor's attribute.
     * @param attributeType: attribute type.
     * @return attribute.
     */
	public Attribute getAttribute(AttributeType attributeType);
}