package gameengine.model.Actions;

import gameengine.model.Actor;

public class NextLevel extends Action {

	public NextLevel(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().changed();
        getActor().notifyObservers("nextLevel");		
	}

}
