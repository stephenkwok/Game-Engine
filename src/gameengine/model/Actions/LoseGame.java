package gameengine.model.Actions;

import gameengine.model.Actions.Action;

import java.util.ArrayList;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

public class LoseGame extends Action {
	private Actor assignedActor;

	public LoseGame(IGameElement assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getGameElement().changed();
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("endGame");
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
