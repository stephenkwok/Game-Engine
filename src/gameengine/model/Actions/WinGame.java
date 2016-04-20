package gameengine.model.Actions;

import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public class WinGame extends Action {

	public WinGame(IPlayActor actor) {
		super(actor);
	}

	@Override
	public void perform() {
        getMyActor().changed();
        ((Observable) getMyActor()).notifyObservers("endGame");		
	}

}
