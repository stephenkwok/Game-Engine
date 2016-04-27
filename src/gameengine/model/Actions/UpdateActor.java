package gameengine.model.Actions;

import gameengine.model.IGameElement;

/**
 * Updates the position and velocity of an actor Usually tied to TickTrigger
 * Actors that never move, like stationary obstacles will not contain this
 * Action
 */
public class UpdateActor extends MovingAction {

	public UpdateActor(IGameElement assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().tick(getMyActor());
	}

}
