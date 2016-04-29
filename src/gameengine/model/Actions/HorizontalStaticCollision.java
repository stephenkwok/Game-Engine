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

	public HorizontalStaticCollision(Actor actor) {
		super(actor);

	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().staticHorizontalCollision(getMyActor());
		System.out.println(getMyActor().getName());
	}

}
