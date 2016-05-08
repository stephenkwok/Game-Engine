package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an elastic vertical collision between Actors. 
 * It's velocity switches direction. 
 * 
 * @author justinbergkamp
 *
 */

public class VerticalBounceCollision extends ActorAction{

	public VerticalBounceCollision(Actor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().verticalBounceCollision(getMyActor());
	}

}
