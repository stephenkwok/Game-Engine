package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeType;

public class GainHealth extends Action{

	private int numLivesGained = 1;
	
	public GainHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().changeAttribute(AttributeType.HEALTH,getNumLivesGained());
	}

	public int getNumLivesGained() {
		return numLivesGained;
	}

	public void setNumLivesGained(int numLivesGained) {
		this.numLivesGained = numLivesGained;
	}


}
