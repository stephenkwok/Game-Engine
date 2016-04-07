package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Actions.Action;

public class GainHealth extends Action{

	private int numLivesGained = 1;
	
	public GainHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().changeAttribute("health",numLivesGained);
	}


}
