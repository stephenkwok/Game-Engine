package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class GainPoints extends Action{

	private int numPointsGained = 1;
	
	public GainPoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().changeAttribute("health",numPointsGained);
	}

}
