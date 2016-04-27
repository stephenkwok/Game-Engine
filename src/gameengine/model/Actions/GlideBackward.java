package gameengine.model.Actions;

import gameengine.model.IGameElement;

public class GlideBackward extends GlidingAction {

	public GlideBackward(IGameElement assignedActor, double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideBackward(getMyActor(),this.getGlideOffset());			
	}

}