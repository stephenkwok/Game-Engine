package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

import gameengine.model.IGameElement;

public class NextLevel extends Action {
	private Actor assignedActor;
	
	public NextLevel(IGameElement assignedElement, Boolean oneTime) {
		super(assignedElement, oneTime);
	}

	@Override
	public void execute() {
		getGameElement().changed();
        ((Observable) getGameElement()).notifyObservers(Arrays.asList(new Object[]{"nextLevel"}));
	}
	
	public Actor getAssignedActor() {
		return assignedActor;
	}

	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}

}
