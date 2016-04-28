package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class WinGame extends Action {
	private Actor assignedActor;

    public WinGame(IGameElement myGameElement, Boolean oneTime) {
        super(myGameElement, oneTime);
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
