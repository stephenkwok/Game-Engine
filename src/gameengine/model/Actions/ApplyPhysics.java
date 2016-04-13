package gameengine.model.Actions;

import gameengine.model.Actor;

public class ApplyPhysics extends Action {

	public ApplyPhysics(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getMyPhysicsEngine().tick(getMyActor());

	}

}
