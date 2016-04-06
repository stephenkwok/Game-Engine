package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;

public class LosePoints extends Action{

	private int numPointsLost = 1;
	
	public LosePoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setPoints(getActor().getPoints()-numPointsLost);
	}

}
