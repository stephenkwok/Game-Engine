package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an elastic sideways collision between Actors
 * 
 * @author justinbergkamp
 *
 */

public class HorizontalBounceCollision extends ActorAction{

	public HorizontalBounceCollision(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().elasticHorizontalCollision(getMyActor());
		
	}

}
