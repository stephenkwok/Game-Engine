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

	public MoveDown(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		 getMyActor().getPhysicsEngine().moveDown(getMyActor());
	}

}
