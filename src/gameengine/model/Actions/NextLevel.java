package gameengine.model.Actions;

import gameengine.model.Actor;

public class NextLevel extends Action {

	public NextLevel(Actor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {
		getActor().changed();
        getActor().notifyObservers("nextLevel");		
	}

}
