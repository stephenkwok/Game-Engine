package gameengine.controller;

import gameengine.model.IActor;
import gameengine.model.ITrigger;

import java.util.List;

/**
 * Created by blakekaplan on 3/30/16.
 */
public interface IGame {

    /**
     * Changes the current level that is being played in the game player
     *
     * @param mylevel The level to be played
     */
    public void setLevel(ILevel mylevel);

    /**
     * Provides a list of all the possible levels to be played
     *
     * @return A list of Level objects that can be played
     */
    public List<ILevel> getLevels();

    /**
     * Provided with a Trigger object from the player, sets off the appropriate response
     *
     * @param myTrigger A Trigger object provided by the game player
     */
    public void handleTrigger(ITrigger myTrigger);

    /**
     * Provides a list of the actors in the current level, each of which may contain a visual Node to be displayed
     *
     * @return A List of Actor objects
     */
    public List<IActor> getActors();
}
