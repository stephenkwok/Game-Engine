package gameengine.model.Actions;


import gameengine.model.IPlayActor;
import gameengine.model.Actions.Action;

/**
 * This Action represents an static sideways collision between Actors
 * Actor will not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */
public class HorizontalStaticCollision extends ActorAction{

	public HorizontalStaticCollision(IPlayActor actor) {
		super(actor);
	}

	
	@Override
	public void perform() {
		getMyActor().getPhysicsEngine().staticHorizontalCollision(getMyActor());
	}

}

