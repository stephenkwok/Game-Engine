package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an elastic vertical collision between Actors
 * 
 * @author justinbergkamp
 *
 */

public class VerticalBounceCollision extends ActorAction{

	public VerticalBounceCollision(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().elasticVerticalCollision(getMyActor());
	}

}
