package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.Action;

public class HorizontalStaticCollision extends Action{

	public HorizontalStaticCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setXVelo(0);
	}

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}

}

