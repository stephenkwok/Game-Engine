package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

/**
 * An example of an Action to glide an Actor right by a given distance (no
 * gravity, no friction).
 *
 * @author michelle
 */
public class GlideRight extends GlidingAction {

    /**
     * Takes in reference to the Actor it will change 
     * 
     * @param assignedActor The Actor that will be changed
     */
	public GlideRight(Actor assignedActor, double offset) {
		super(assignedActor, offset);
	}

	/**
	 * Moves the Actor to the right through gliding
	 */
	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideRight(getMyActor(),this.getGlideOffset());		

	}

}
