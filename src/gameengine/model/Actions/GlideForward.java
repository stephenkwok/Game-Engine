package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class GlideForward extends GlidingAction {

	public GlideForward(IPlayActor actor1) {
		super(actor1);
	}

	@Override
	public void perform() {
		
    	getMyActor().getPhysicsEngine().glideForward(getMyActor());			
	}

}