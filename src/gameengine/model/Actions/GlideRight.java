package gameengine.model.Actions;

import gameengine.model.Actor;

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
	public GlideRight(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	/**
	 * Moves the Actor to the right through gliding
	 */
	@Override
	public void perform() {
		getMyActor().setHeading(0);
		getMyActor().setDirection();

    	getMyActor().getPhysicsEngine().glideRight(getMyActor(),this.getGlideOffset());		

	}

}
