package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public class ApplyPhysics extends ActorAction {

	public ApplyPhysics(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().tick(getMyActor());

	}

}
