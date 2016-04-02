package usecases;

import gameengine.controller.ILevel;
import gameengine.model.IActor;
import gameengine.model.ITrigger;
import javafx.scene.image.Image;

import java.util.*;

import authoringenvironment.model.IEditableGameElement;

/**
 * A Level is essentially a package of Actor objects. It is able to relay a Trigger to Actors when it receives one.
 *
 * @author blakekaplan
 */
public class Level implements ILevel, IEditableGameElement {

    List<IActor> myActors;
    Map<ITrigger, List<IActor>> triggerMap;
    String myName;
    Image myBackground;

    /**
     * Instantiates the triggerMap and Actor list
     */
    public Level() {
        myActors = new ArrayList<>();
        triggerMap = new HashMap<>();
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
     * Adds a new Actor to the Level and updates the triggerMap accordingly
     *
     * @param newActor The Actor to be added to the Level
     */
    @Override
    public void addActor(IActor newActor) {
        myActors.add(newActor);
        Set<ITrigger> actorTriggers = newActor.getTriggers();
        for (ITrigger myTrigger : actorTriggers) {
            if (triggerMap.containsKey(myTrigger)) {
                List<IActor> levelActors = triggerMap.get(myTrigger);
                levelActors.add(newActor);
                triggerMap.put(myTrigger, levelActors);
            } else {
                List<IActor> levelActors = new ArrayList<>();
                levelActors.add(newActor);
                triggerMap.put(myTrigger, levelActors);
            }
        }
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

	@Override
	public Image getImage() {
		return myBackground;
	}

	@Override
	public void setImage(Image image) {
		this.myBackground = image;
	}


}
