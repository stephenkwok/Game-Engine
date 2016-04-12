package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeType;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class LoseHealth extends Action{



	private int numLivesLost = 1;
	
	public LoseHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().changeAttribute(AttributeType.HEALTH,-getNumLivesLost() );
	}

	public int getNumLivesLost() {
		return numLivesLost;
	}

	public void setNumLivesLost(int numLivesLost) {
		this.numLivesLost = numLivesLost;
	}
	
}
