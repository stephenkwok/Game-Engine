package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.Action;

/**
 * This Action represents an elastic vertical collision between Actors
 * 
 * @author justinbergkamp
 *
 */
public class VerticalBounceCollision extends Action{

	public VerticalBounceCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		//myPhysicsEngine.vertBounceCollision(getActor());
		getActor().setYVelo(getActor().getYVelo()*-1);
	}

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}

}

