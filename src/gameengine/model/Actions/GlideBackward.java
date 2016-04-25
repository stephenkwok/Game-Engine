package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideBackward extends GlidingAction {

	public GlideBackward(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().glideBackward(getMyActor());
	}

}