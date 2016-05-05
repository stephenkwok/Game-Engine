package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This action glides the actor up by a given offset. 
 * @author justinbergkamp
 */
public class GlideUp extends GlidingAction {

	public GlideUp(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
			getMyActor().setHeading(90);
			getMyActor().setDirection();

		    getMyActor().getPhysicsEngine().glideUp(getMyActor(),this.getGlideOffset());					
	}

}
