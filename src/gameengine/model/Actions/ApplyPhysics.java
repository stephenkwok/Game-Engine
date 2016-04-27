package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public class ApplyPhysics extends ActorAction {

	public ApplyPhysics(IGameElement actor) {
		super((IPlayActor) actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());

	}

}
