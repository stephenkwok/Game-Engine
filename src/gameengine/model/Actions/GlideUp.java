package gameengine.model.Actions;

import gameengine.model.IGameElement;

public class GlideUp extends GlidingAction {

	public GlideUp(IGameElement assignedActor, double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideUp(getMyActor(),this.getGlideOffset());					
	}

}
