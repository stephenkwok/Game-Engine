package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.controller.Action;

public class LoseHealth extends Action{

	private int numLivesLost = 1;
	
	public LoseHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setHealth( getActor().getHealth()-numLivesLost );
	}

}
