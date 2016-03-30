package gameengine.controller;

import gameengine.model.ITrigger;

/**
 * Created by blakekaplan on 3/30/16.
 */
public interface ILevel {

    /**
     * Provided with a Trigger object, sets off the appropriate response
     *
     * @param myTrigger A particular Trigger object sent from the game player
     */
    public void handleTrigger(ITrigger myTrigger);

    /**
     * Sets the Level's name
     *
     * @param name A name for the Level
     */
    public void setName(String name);

    /**
     * Provides the Level's name
     *
     * @return The name of the Level object
     */
    public String getName();

}
