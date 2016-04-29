package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class WinGame extends Action {
	private Actor assignedActor;

    public WinGame(IGameElement myGameElement) {
        super(myGameElement);
    }

    @Override
    public void perform() {
        getGameElement().changed();
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("winGame");
        ((Observable) getGameElement()).notifyObservers(myList);
    }

	public Actor getAssignedActor() {
		return assignedActor;
	}

	public void setAssignedActor(Actor assignedActor) {
		this.assignedActor = assignedActor;
	}
}
