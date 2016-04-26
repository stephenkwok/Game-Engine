package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class GlideLeft extends GlidingAction {

	public GlideLeft(IPlayActor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().glideLeft(getMyActor());
	}

}
