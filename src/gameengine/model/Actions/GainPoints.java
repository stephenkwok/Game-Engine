package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class GainPoints extends Action{

	private int numPointsGained = 1;
	private int myPointsLost = 1;
	
	public GainPoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setPoints(getActor().getPoints()+numPointsGained);
	}

}
