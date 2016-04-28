package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideBackward extends GlidingAction {

	public GlideBackward(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideBackward(getMyActor(),this.getGlideOffset());			
	}

}