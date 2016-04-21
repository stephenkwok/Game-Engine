package gameengine.model;

import java.util.List;
import java.util.Map;
import gameengine.model.Actions.Action;
import javafx.geometry.Bounds;

public interface IPlayActor {
	
	
	/**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param myTrigger The string representation of the trigger to be executed
     */
    public void performActionsFor(ITrigger myTrigger);
    
    /**
     * Assigns a phyiscs engine to an Actor
     * @param physicsEngine The assigned physics engine
     */
    public void setPhysicsEngine(PhysicsEngine physicsEngine);
    
    /**
     * Provides the Actor's physics engine
     * @return  The Actor's phyiscs engine
     */
    public PhysicsEngine getPhysicsEngine();
	
    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change);
    
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


	public String getName();

	public Attribute getAttribute(AttributeType health);

	public double getFriction();
	
	public Bounds getBounds();

	public boolean checkState(ActorState state);

	public void removeState(ActorState state);

	public void addState(ActorState state);

	public Map<String, List<Rule>> getRules();

    
}