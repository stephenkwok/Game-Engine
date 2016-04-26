package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveDown extends MovingAction {

	private static final int DOWN_ANGLE = 270;

	public MoveDown(IPlayActor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		// getActor().getPhysicsEngine().moveDown(getActor());
	}

}
