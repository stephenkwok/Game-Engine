package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.controller.Action;

public class VerticalBounceCollision extends Action{

	public VerticalBounceCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		getActor().setYVelo(getActor().getYVelo()*-1);
	}

}

