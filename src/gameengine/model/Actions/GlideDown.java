package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideDown extends GlidingAction {

	public GlideDown(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
		System.out.println(getMyActor().getPhysicsEngine());
    	getMyActor().getPhysicsEngine().glideDown(getMyActor(),this.getGlideOffset());			

	}

}
