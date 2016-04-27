package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends ActorAction {

	public MovingAction(IGameElement actor) {
		super((IPlayActor) actor);
	}

	@Override
	public abstract void perform();
}
