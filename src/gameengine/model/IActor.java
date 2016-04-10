package gameengine.model;


import java.util.Set;

/**
 * This interface defines the the public methods for Actor objects. Each Actor will have a position, a number of points, a designated
 * amount of health, and sets of rules. For Rules, each Trigger will have corresponding Action objects associated with it
 * that define the procedure to be performed when the Trigger is set off.
 *
 * @author blakekaplan
 */
public interface IActor {

    /**
     * Gets the Actor's X location
     *
     * @return The Actor's X coordinate
     */
    public double getX();

    /**
     * Gets the Actor's Y location
     *
     * @return The Actor's Y coordinate
     */
    public double getY();
    
    /**
     * Gets the Actor's velocity along the horizontal vector
     * 
     * @return the Actor's X velocity
     */
    public double getXVelo();

    /**
     * Gets the Actor's velocity along the vertical vector
     * 
     * @return the Actor's Y velocity
     */
    public double getYVelo();
    
    /**
     * Sets an Actor's X position
     * @param updateXPosition
     */
	public void setXPos(double updateXPosition); 
	
	/**
     * Sets an Actor's Y position
     * @param updateYPosition
     */
	public void setYPos(double updateYPosition); 
	
	/**
     * Sets an Actor's X velocity
     * @param updateXVelo
     */
	public void setXVelo(double updateXVelo); 
	
	/**
     * Sets an Actor's Y velocity
     * @param updateYVelo
     */
	public void setYVelo(double updateYVelo);

    /**
     * Moves the Actor's current position
     *
     * @param distance  The distance to move the Actor
     * @param direction The direction that the Actor should move in
     */
    public void move(double distance, double direction);

    /**
     * Provides the list of Triggers that the Actor responds to
     *
     * @return The list of Triggers
     */
    public Set<String> getTriggers();

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    public void addRule(IRule newRule);

    /**
     * Provides the Actor's ID
     *
     * @return The Actor's ID
     */
    public int getID();

	void performActionsFor(String triggerString);

}
