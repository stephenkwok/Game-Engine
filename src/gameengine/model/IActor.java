package gameengine.model;


import javafx.scene.Node;

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
     * Gets the Actor's amount of health
     *
     * @return The Actor's amount of health
     */
    public double getHealth();

    /**
     * Gets the Actor's number of points
     *
     * @return The Actor's number of points
     */
    public double getPoints();

    /**
     * Moves the Actor's current position
     *
     * @param distance  The distance to move the Actor's
     * @param direction The direction that the Actor should move in
     */
    public void move(double distance, double direction);

    /**
     * Changes the Actor's number of points
     *
     * @param change The desired change in the Actor's number of points
     */
    public void changePoints(double change);


    /**
     * Changes the Actor's amount of health
     *
     * @param change The desired change in the Actor's amount of health
     */
    public void changeHealth(double change);

    /**
     * Performs the action for a particular provided trigger
     *
     * @param myTrigger A Trigger object that calls for an appropriate response
     */
    public void performActionFor(ITrigger myTrigger);

    /**
     * Provides the a visual element reflecting the Actor's current characteristics
     *
     * @return A JavaFX Node that can be displayed in the UI
     */
    public Node getUINode();

}
