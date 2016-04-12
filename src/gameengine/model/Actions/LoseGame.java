package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class LoseGame extends Action {
	private Actor assignedActor;
	
	public LoseGame(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().changed();
        getMyActor().notifyObservers("endGame");		
	}

	public Actor getAssignedActor() {
		return assignedActor;
	}

	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}

}
