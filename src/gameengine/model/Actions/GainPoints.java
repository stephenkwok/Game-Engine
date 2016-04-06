package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;

public class GainPoints extends Action{

	private int numPointsGained = 1;
	
	public GainPoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setPoints(getActor().getPoints()+numPointsGained);
	}

}
