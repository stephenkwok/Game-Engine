package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(Actor actor,Double offset, Boolean oneTime) {
		super(actor, oneTime);
		glideOffset = offset;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),glideOffset, isOneTime()};
	}

	public Double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void execute();

}
