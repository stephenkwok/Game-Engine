package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This Action represents an static sideways collision between Actors Actor will
 * not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */

public class HorizontalStaticCollision extends ActorAction{

	public HorizontalStaticCollision(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().getPhysicsEngine().staticHorizontalCollision(getMyActor());
	}

}
