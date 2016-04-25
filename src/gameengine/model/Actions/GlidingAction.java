package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public abstract class GlidingAction extends Action {

	public GlidingAction(IPlayActor actor1) {
		super(actor1);
	}

	@Override
	public abstract void perform();

}
