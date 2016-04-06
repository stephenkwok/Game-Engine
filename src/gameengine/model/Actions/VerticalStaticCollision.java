package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.controller.Action;

public class VerticalStaticCollision extends Action{

	public VerticalStaticCollision(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		getActor().setYVelo(0);
	}


}