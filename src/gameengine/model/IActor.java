package gameengine.model;


import javafx.scene.image.ImageView;

import java.util.Set;

/**
 * This interface defines a limited subset of Actor functionality.
 * We have decided to use this interface to limit outside access to data that an Actor contains.
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
    public double getVeloX();

    /**
     * Gets the Actor's velocity along the vertical vector
     *
     * @return the Actor's Y velocity
     */
    public double getVeloY();

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    public void addRule(IRule newRule);

    /**
     * Perform's the Actor's actions for a particular trigger
     *
     * @param triggerString The string representing the specific trigger sent to the Actor
     */
    public void performActionsFor(String triggerString);

    /**
     * Provides the Actor's ImageView
     *
     * @return The Actor's ImageView
     */
    public ImageView getMyImageView();

    /**
     * Provides the Actor's name
     *
     * @return The Actor's name
     */
    public String getMyName();

}