package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * This Action represents an elastic sideways collision between Actors
 * 
 * @author justinbergkamp
 *
 */

public class HorizontalBounceCollision extends ActorAction{

	public HorizontalBounceCollision(Actor assignedActor, Boolean oneTime) {
		super(assignedActor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().elasticHorizontalCollision(getMyActor());
	}

}
