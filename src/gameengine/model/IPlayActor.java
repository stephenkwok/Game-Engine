package gameengine.model;

import java.util.Observer;
import java.util.Set;

import javafx.beans.Observable;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public interface IPlayActor {
	
	
	/**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param triggerString The string representation of the trigger to be executed
     */
    public void performActionsFor(String triggerString);
    
    /**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    public Set<String> getTriggers();
    
    /**
     * Assigns a phyiscs engine to an Actor
     * @param physicsEngine The assigned physics engine
     */
    public void setEngine(PhysicsEngine physicsEngine);
    
    /**
     * Provides the Actor's physics engine
     * @return  The Actor's phyiscs engine
     */
    public PhysicsEngine getMyPhysicsEngine();
    
    /**
     * Marks the Actor as dead
     * @return  A boolean representing whether or not the Actor is dead
     */
    public boolean isDead();
    
	/**
	 * Return whether the actor is a playable, main character.
	 * @return
	 */
	public boolean isMain();
	
    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change);
    
    /**
     * Sets the Actor to alive or dead
     * @param isDead    The desired Actor state
     */
    public void setDead(boolean isDead);
    
    /**
     * Sets the Actor's X coordinate
     * @param updateXPosition   The new X coordinate
     */
    public void setX(double updateXPosition);
    
    /**
     * Sets the Actor's Y position
     * @param updateYPosition   The new Y position
     */
    public void setY(double updateYPosition);
    
    /**
     * Sets a new X velocity
     * @param updateXVelo   The new X velocity
     */
    public void setVeloX(double updateXVelo);
    
    /**
     * Sets a new Y velocity
     * @param updateYVelo   The new Y velocity
     */
    public void setVeloY(double updateYVelo);
    
    /**
     * Provides the Actor's X Velocity
     * @return  The Actors X Velocity
     */
    public double getVeloX();
    
    /**
     * Provides the Actor's Y Velocity
     * @return  The Actor's Y Velocity
     */
    public double getVeloY();
    
    /**
     * Provides the Actor's X coordinate
     * @return  The Actor's X coordinate
     */
    public double getX();
    
    /**
     * Provides the Actor's Y coordinate
     * @return  The Actor's Y coordinate
     */
    public double getY();
    
    /**
     * Sets the Actor as changed
     */
    public void changed();

	public void setInAir(boolean b);

	public String getMyName();

	public Object getAttribute(AttributeType health);

	public boolean isInAir();

	public double getMyFriction();
	
	public Bounds getBounds();

    
}
