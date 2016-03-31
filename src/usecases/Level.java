package usecases;

import gameengine.controller.ILevel;
import gameengine.model.IActor;
import gameengine.model.ITrigger;

import java.util.*;

/**
 * @author blakekaplan
 */
public class Level implements ILevel {

    List<IActor> myActors;
    Map<ITrigger, List<IActor>> triggerMap;
    String myName;

    /**
     * Instantiates the triggerMap and populates it based on provided information
     *
     * @param actors The provided List of Actors to be accounted for
     */
    public Level(List<IActor> actors) {
        myActors = actors;
        triggerMap = new HashMap<>();
        populateMap(actors);
    }

    /**
     * Populates triggerMap based on the provided list of Actors
     *
     * @param actors The list of Actors to be accounted for
     */
    private void populateMap(List<IActor> actors) {
        for (IActor myActor : actors) {
            Set<ITrigger> actorTriggers = myActor.getTriggers();
            for (ITrigger myTrigger : actorTriggers) {
                if (triggerMap.containsKey(myTrigger)) {
                    List<IActor> levelActors = triggerMap.get(myTrigger);
                    levelActors.add(myActor);
                    triggerMap.put(myTrigger, levelActors);
                } else {
                    List<IActor> levelActors = new ArrayList<>();
                    levelActors.add(myActor);
                    triggerMap.put(myTrigger, levelActors);
                }
            }
        }
    }

    /**
     * Calls for the appropriate response upon receiving a particular Trigger
     *
     * @param myTrigger A particular Trigger object sent from the game player
     */
    @Override
    public void handleTrigger(ITrigger myTrigger) {
        List<IActor> relevantActors = triggerMap.get(myTrigger);
        for (IActor myActor : relevantActors) {
            myActor.performActionsFor(myTrigger);
        }
    }

    /**
     * Sets the Level's name
     *
     * @param name A name for the Level
     */
    @Override
    public void setName(String name) {
        myName = name;
    }

    /**
     * Provides the Level's name
     *
     * @return The Level's name
     */
    @Override
    public String getName() {
        return myName;
    }

    /**
     * Provides the list of Actors that are present in the Level
     *
     * @return The List of Actors in the Level
     */
    @Override
    public List<IActor> getActors() {
        return myActors;
    }
}
