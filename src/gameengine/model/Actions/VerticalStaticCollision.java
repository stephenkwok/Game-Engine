package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * This Action represents an static vertical collision between Actors Actor will
 * not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */

public class VerticalStaticCollision extends ActorAction{

	public VerticalStaticCollision(IGameElement actor) {
		super((IPlayActor) actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().staticVerticalCollision(getMyActor());
	}

}