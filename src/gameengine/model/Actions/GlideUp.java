package gameengine.model.Actions;

import gameengine.model.Actor;

public class GlideUp extends GlidingAction {

	public GlideUp(Actor assignedActor, double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
		
		//getMyActor().setY(getMyActor().getY()-this.getGlideOffset());
    	getMyActor().getPhysicsEngine().glideUp(getMyActor(),this.getGlideOffset());					
	}

}
