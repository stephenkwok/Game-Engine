package gameengine.model.Actions;

import gameengine.model.IGameElement;

public class GlideLeft extends GlidingAction {

	public GlideLeft(IGameElement assignedActor, double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideLeft(getMyActor(),this.getGlideOffset());			

	}

}
