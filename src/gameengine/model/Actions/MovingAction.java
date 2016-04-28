package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends ActorAction {

	public MovingAction(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override
	public abstract void execute();
}
