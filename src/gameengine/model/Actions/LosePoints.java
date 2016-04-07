package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class LosePoints extends Action{

	private int numPointsLost = 1;
	
	public LosePoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setPoints(getActor().getPoints()-numPointsLost);
	}

}
