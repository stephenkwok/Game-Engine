package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideForward extends GlidingAction {

	public GlideForward(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideForward(getMyActor());			
	}

}