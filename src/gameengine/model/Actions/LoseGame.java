package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;

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

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}

}
