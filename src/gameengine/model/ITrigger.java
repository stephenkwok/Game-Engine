package gameengine.model;

import gameengine.controller.Actor;

/**
 * This interface defines the public methods for Trigger objects. Trigger correspond with the different events that can
 * cause an Action response.
 *
 * @author blakekaplan
 */
public interface ITrigger {

    /**
     * Provides the name of the Trigger
     *
     * @return The Trigger's name
     */
    public String getTriggerName();

    /**
     * Checks a boolean condition against the state of an actor
     *
     * @param myActor
     * @return A boolean that says if the condition is true or false
     */
    public boolean evaluate(Actor myActor);

}
