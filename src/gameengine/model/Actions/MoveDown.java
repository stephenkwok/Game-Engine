package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveDown extends MovingAction {

	private static final int DOWN_ANGLE = 270;

	public MoveDown(Actor assignedActor, Boolean oneTime) {
		super(assignedActor, oneTime);
	}

	@Override
	public void execute() {
		// getActor().getPhysicsEngine().moveDown(getActor());
	}

}
