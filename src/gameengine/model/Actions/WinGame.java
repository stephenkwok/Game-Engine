package gameengine.model.Actions;

import java.awt.List;
import java.util.ArrayList;
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
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("endGame");
        ((Observable) getMyActor()).notifyObservers(myList);		
	}

}
