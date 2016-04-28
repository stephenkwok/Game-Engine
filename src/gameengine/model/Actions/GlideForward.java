package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideForward extends GlidingAction {

	public GlideForward(Actor actor, Double offset) {
		super(actor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());			

	}

}