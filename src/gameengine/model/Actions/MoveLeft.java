package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class MoveLeft extends MovingAction {

	private static final double LEFT_DIRECTION = 180;

	public MoveLeft(Actor actor) {
		super(actor);
	}

	@Override

	public void perform() {
		getMyActor().getPhysicsEngine().moveLeft(getMyActor());
		getMyActor().setHeading(180);
		getMyActor().setDirection();
	}
}
