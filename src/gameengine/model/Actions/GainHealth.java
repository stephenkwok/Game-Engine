package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.controller.Action;

public class GainHealth extends Action{

	private int numLivesGained = 1;
	
	public GainHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setHealth( getActor().getHealth()+numLivesGained );
	}

}
