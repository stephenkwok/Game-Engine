package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.Action;

public class LoseHealth extends Action{



	private int numLivesLost = 1;
	
	public LoseHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setHealth( getActor().getHealth()-numLivesLost );
	}

	@Override
	public void performOn(Actor a) {
		a.setHealth(a.getHealth()-numLivesLost);
	}


	
}
