package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeType;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class LosePoints extends Action{

	private int numPointsLost = 1;
	
	public LosePoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().changeAttribute(AttributeType.POINTS,-getNumPointsLost());
	}

	public int getNumPointsLost() {
		return numPointsLost;
	}

	public void setNumPointsLost(int numPointsLost) {
		this.numPointsLost = numPointsLost;
	}

}
