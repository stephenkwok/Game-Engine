package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class GlideForward extends GlidingAction {

	public GlideForward(IPlayActor actor1, double offset) {
		super(actor1, offset);
	}

	@Override
	public void perform() {
		
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());			
	}

}