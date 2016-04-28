package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideUp extends GlidingAction {

	public GlideUp(Actor assignedActor, Double offset, Boolean oneTime) {
		super(assignedActor, offset, oneTime);
	}

	@Override
	public void execute() {
    	getMyActor().getPhysicsEngine().glideUp(getMyActor(),this.getGlideOffset());					
	}

}
