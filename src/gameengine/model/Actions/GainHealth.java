package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.controller.Action;

public class GainHealth extends Action{

	private int numLivesGained = 1;
	
	public GainHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setHealth( getActor().getHealth()+numLivesGained );
	}


}
