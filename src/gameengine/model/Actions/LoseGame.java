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
		getActor().changed();
        getActor().notifyObservers("WINGAME");		
	}

}
