package gameengine.model.Actions;

import gameengine.model.IPlayActor;

public class ApplyPhysics extends ActorAction {

	public ApplyPhysics(IPlayActor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());

	}

}
