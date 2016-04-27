package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.Actions.Action;

/**
 * This Action represents an elastic vertical collision between Actors
 * 
 * @author justinbergkamp
 *
 */

public class VerticalBounceCollision extends ActorAction{

	public VerticalBounceCollision(IGameElement actor) {
		super((IPlayActor) actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().elasticVerticalCollision(getMyActor());
	}

}
