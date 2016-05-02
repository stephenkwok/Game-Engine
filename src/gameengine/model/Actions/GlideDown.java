package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * Glides the actor downwards by a given offset.
 * @author justinbergkamp
 */
public class GlideDown extends GlidingAction {

	public GlideDown(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
			getMyActor().setHeading(270);
			getMyActor().setDirection();
		    getMyActor().getPhysicsEngine().glideDown(getMyActor(),this.getGlideOffset());					
	}

}
