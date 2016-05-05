package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This abstact class is a superclass for all actions that involve or result in movement
 * @author blakekaplan
 */
public abstract class MovingAction extends ActorAction {

	public MovingAction(Actor actor) {
		super(actor);
	}

	@Override
	public abstract void perform();
}
