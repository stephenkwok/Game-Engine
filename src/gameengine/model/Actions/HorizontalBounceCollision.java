package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.controller.Action;

public class HorizontalBounceCollision extends Action{

	public HorizontalBounceCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		getActor().setXVelo(getActor().getXVelo()*-1);
	}

}
