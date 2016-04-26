package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(IPlayActor actor1,double offset) {
		super(actor1);
		glideOffset = offset;
	}

	public double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void perform();

}
