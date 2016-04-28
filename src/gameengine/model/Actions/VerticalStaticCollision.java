package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an static vertical collision between Actors Actor will
 * not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */

public class VerticalStaticCollision extends ActorAction{

	public VerticalStaticCollision(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().staticVerticalCollision(getMyActor());
	}

}