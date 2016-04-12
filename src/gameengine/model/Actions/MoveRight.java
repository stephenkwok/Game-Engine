package gameengine.model.Actions;


import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * An example of an Action to move an Actor right by a given distance.
 *
 * @author blakekaplan
 */
public class MoveRight extends MovingAction {

    private static final int RIGHT_ANGLE = 0;

    /**
     * Takes in reference to the Actor it will change along with the argument it will require to do so
     *
     * @param assignedActor The Actor that will be changed
     * @param args          The arguments required to perform the change
     */
    public MoveRight(Actor assignedActor) {
        super(assignedActor);
    }

	/**
     * Moves the Actor to the right by the distance provided in the arguments
     */
    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().moveRight(getMyActor());
    }

    
}
