package gameengine.model.Actions;

import gameengine.model.IGameElement;

public class GlideForward extends GlidingAction {

	public GlideForward(IGameElement actor1, double offset) {
		super(actor1, offset);
	}

	@Override
	public void perform() {
		
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());			

	}

}