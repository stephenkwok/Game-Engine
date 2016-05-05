package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class LoseGame extends Action {
	private Actor assignedActor;

	public LoseGame(IGameElement assignedElement) {
		super(assignedElement);
	}

	@Override
	public void perform() {
		getGameElement().changed();
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("loseGame");
		getGameElement().changed();
        ((Observable) getGameElement()).notifyObservers(myList);
	}

	public Actor getAssignedActor() {
		return assignedActor;
	}

	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}

}
