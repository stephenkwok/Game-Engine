package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideUp extends GlidingAction {

	public GlideUp(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
    	getActor().getPhysicsEngine().glideUp(getActor());					
	}

}
