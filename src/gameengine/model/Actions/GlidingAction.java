package gameengine.model.Actions;

import gameengine.model.Actor;

public abstract class GlidingAction extends ActorAction {

	Double glideOffset;
	
	public GlidingAction(Actor actor,Double offset) {
		super(actor);
		glideOffset = offset;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),glideOffset};
	}

	public Double getGlideOffset() {
		return glideOffset;
	}
	
	@Override
	public abstract void perform();

}
