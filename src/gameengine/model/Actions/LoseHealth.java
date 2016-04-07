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
		getActor().changeAttribute(AttributeType.HEALTH,-numLivesLost );
	}
	
}
