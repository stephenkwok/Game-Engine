package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Actions.MovingAction;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveLeft extends MovingAction {

	private static final double LEFT_DIRECTION = 180;

	public MoveLeft(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override

	public void execute() {
		getMyActor().getPhysicsEngine().moveLeft(getMyActor());
		getMyActor().setHeading(180);
		getMyActor().setDirection();
	}
}
