package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends ActorAction {

	public MovingAction(Actor actor) {
		super(actor);
	}

	@Override
	public abstract void perform();
}
