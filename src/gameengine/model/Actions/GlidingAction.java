package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(IGameElement actor1,double offset) {
		super((IPlayActor) actor1);
		glideOffset = offset;
	}

	public double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void perform();

}
