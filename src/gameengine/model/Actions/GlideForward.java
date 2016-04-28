package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideForward extends GlidingAction {

	public GlideForward(Actor actor, Double offset, Boolean oneTime) {
		super(actor, offset, oneTime);
	}

	@Override
	public void execute() {
		
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());			

	}

}