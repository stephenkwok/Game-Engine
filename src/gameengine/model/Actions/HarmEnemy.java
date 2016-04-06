package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class HarmEnemy extends Action {

	private int damage = 1;
	
	public HarmEnemy(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		getActor().setHealth(getActor().getHealth()-damage);
	}

	@Override
	public void performOn(Actor a) {
		//nothing happens to character
	}

}
