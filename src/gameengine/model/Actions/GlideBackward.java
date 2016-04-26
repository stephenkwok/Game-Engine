package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class GlideBackward extends GlidingAction {

	public GlideBackward(Actor assignedActor, double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideBackward(getMyActor(),this.getGlideOffset());			
	}

}