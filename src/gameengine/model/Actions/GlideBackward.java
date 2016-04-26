package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class GlideBackward extends GlidingAction {

	public GlideBackward(IPlayActor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().glideBackward(getMyActor());
	}

}