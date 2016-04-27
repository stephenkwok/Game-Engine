package gameengine.model.Actions;

import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * This Action represents an static sideways collision between Actors Actor will
 * not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */

public class HorizontalStaticCollision extends ActorAction{

	public HorizontalStaticCollision(IGameElement actor) {
		super((IPlayActor) actor);
	}

	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().staticHorizontalCollision(getMyActor());
	}

}
