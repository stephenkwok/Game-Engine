package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public class ApplyPhysics extends ActorAction {

	public ApplyPhysics(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());

	}

}
