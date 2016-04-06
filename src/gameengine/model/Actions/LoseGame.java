package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.model.Action;

public class LoseGame extends Action {
	private Actor assignedActor;
	public LoseGame(Actor assignedActor) {
		super(assignedActor);
		this.assignedActor = assignedActor;
	}

	@Override
	public void perform() {
		assignedActor.notifyObservers("endGame");

	}

}
