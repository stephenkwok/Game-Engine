package gameengine.model.Actions;

import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class NextLevel extends Action {

	public NextLevel(IPlayActor assignedActor) {
		super(assignedActor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {

		getGameElement().changed();
        ((Observable) getGameElement()).notifyObservers("nextLevel");
	}

}
