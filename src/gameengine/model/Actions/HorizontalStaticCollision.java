package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.Action;

/**
 * This Action represents an static sideways collision between Actors
 * Actor will not bounce off, merely lose its velocity
 * 
 * @author justinbergkamp
 *
 */
public class HorizontalStaticCollision extends Action{

	public HorizontalStaticCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		//myPhysicsEngine.horzStaticCollision(getActor());
		getActor().setXVelo(0);
	}

}

