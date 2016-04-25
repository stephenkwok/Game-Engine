package gameengine.model.Actions;

import gameengine.model.IPlayActor;
import gameengine.model.Actions.Action;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends ActorAction {

	public MovingAction(IPlayActor actor) {
		super(actor);
	}

	@Override
	public abstract void perform();
}
