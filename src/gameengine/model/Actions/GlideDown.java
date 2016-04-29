package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideDown extends GlidingAction {

	public GlideDown(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
		    getMyActor().getPhysicsEngine().glideDown(getMyActor(),this.getGlideOffset());					
	}

}
