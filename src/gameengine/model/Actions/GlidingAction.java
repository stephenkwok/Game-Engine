package gameengine.model.Actions;

import gameengine.model.Actor;

public abstract class GlidingAction extends Action {

	public GlidingAction(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public abstract void perform();

}
