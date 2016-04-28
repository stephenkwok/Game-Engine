package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideBackward extends GlidingAction {

	public GlideBackward(Actor assignedActor, Double offset, Boolean oneTime) {
		super(assignedActor, offset, oneTime);
	}

	@Override
	public void execute() {
    	getMyActor().getPhysicsEngine().glideBackward(getMyActor(),this.getGlideOffset());			
	}

}