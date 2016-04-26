package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

/**
 * Updates the position and velocity of an actor Usually tied to TickTrigger
 * Actors that never move, like stationary obstacles will not contain this
 * Action
 */
public class UpdateActor extends MovingAction {

	public UpdateActor(IPlayActor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());
	}

}
