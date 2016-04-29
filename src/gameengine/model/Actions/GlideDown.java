package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideDown extends GlidingAction {

	public GlideDown(Actor actor, Double offset) {
		super(actor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideDown(getMyActor(),this.getGlideOffset());			

	}

}