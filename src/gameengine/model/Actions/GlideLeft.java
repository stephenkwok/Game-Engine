package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideLeft extends GlidingAction {

	public GlideLeft(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
    	getMyActor().getMyPhysicsEngine().glideLeft(getMyActor());			
	}

}
