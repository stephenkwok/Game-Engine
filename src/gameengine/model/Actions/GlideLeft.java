package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideLeft extends GlidingAction {

	public GlideLeft(Actor assignedActor, Double offset, Boolean oneTime) {
		super(assignedActor, offset, oneTime);
	}

	@Override
	public void execute() {
    	getMyActor().getPhysicsEngine().glideLeft(getMyActor(),this.getGlideOffset());			

	}

}
