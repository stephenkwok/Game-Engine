package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(Actor actor,double offset) {
		super(actor);
		glideOffset = offset;
	}

	public double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void perform();

}
