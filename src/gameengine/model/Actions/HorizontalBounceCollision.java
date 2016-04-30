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

	public HorizontalBounceCollision(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().elasticHorizontalCollision(getMyActor());
		}

}
