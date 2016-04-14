package gameengine.model.Actions;

import java.util.Observable;

import gameengine.model.Actor;

public class WinGame extends Action {

	public WinGame(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
        getMyActor().changed();
        ((Observable) getMyActor()).notifyObservers("endGame");		
	}

}
