package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;

public class LoseHealth extends Action {

	private int numLivesLost = 1;
	
	public LoseHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setHealth(getActor().getHealth()-numLivesLost);
	}
	
}
