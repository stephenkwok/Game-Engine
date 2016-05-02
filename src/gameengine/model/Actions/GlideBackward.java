package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 *  This action glides an actor forwards(in the direction of the actor's heading) by its given offset. 
 * 
 * @author justinbergkamp
 *
 */
public class GlideBackward extends GlidingAction {

	public GlideBackward(Actor assignedActor, Double offset) {
		super(assignedActor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideBackward(getMyActor(),this.getGlideOffset());			
	}

}