package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;

public class GainPoints extends Action{

	private int numPointsGained = 1;
	private int myPointsLost = 1;
	
	public GainPoints(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setPoints(getActor().getPoints()+numPointsGained);
	}

	@Override
	public void performOn(Actor a) {
		a.setPoints(a.getPoints()+numPointsGained);
		getActor().setPoints(getActor().getPoints()-myPointsLost);
	}

}
