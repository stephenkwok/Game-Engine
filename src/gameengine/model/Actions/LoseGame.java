package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class LoseGame extends Action {
	private Actor assignedActor;
	public LoseGame(Actor assignedActor) {
		super(assignedActor);
		this.assignedActor = assignedActor;
	}

	@Override
	public void perform(PhysicsEngine myPhysicsEngine) {
		// TODO Auto-generated method stub
		
	}

}
