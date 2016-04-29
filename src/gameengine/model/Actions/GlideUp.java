package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideUp extends GlidingAction {

	public GlideUp(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
		    getMyActor().getPhysicsEngine().glideUp(getMyActor(),this.getGlideOffset());					
	}

}
