package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.Actions.Action;

/**
 * This Action represents an static vertical collision between Actors
 * Actor will not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */
public class VerticalStaticCollision extends ActorAction{

	public VerticalStaticCollision(IPlayActor actor) {
		super(actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().staticVerticalCollision(getMyActor());
	}


}