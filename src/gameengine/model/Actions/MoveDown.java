package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * Moves the parameter actor down by a force
 * @author blakekaplan
 */
public class MoveDown extends MovingAction {

	public MoveDown(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		 getMyActor().getPhysicsEngine().moveDown(getMyActor());
	}

}
