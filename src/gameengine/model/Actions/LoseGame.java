package gameengine.model.Actions;

import gameengine.model.Actions.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

public class LoseGame extends Action {
	private Actor assignedActor;

	public LoseGame(IGameElement assignedElement, Boolean oneTime) {
		super(assignedElement, oneTime);
	}

	@Override
	public void execute() {
		getGameElement().changed();
        ((Observable) getGameElement()).notifyObservers(Arrays.asList(new Object[]{"endGame"}));
	}

	public Actor getAssignedActor() {
		return assignedActor;
	}

	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}

}
