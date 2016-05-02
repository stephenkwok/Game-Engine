package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This action glides an actor forwards(in the direction of the actor's heading) by its given offset. 
 * 
 * @author justinbergkamp
 */
public class GlideForward extends GlidingAction {

	public GlideForward(Actor actor, Double offset) {
		super(actor, offset);
	}

	@Override
	public void perform() {
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());			

	}

}