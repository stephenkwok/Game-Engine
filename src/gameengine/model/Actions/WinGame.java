package gameengine.model.Actions;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public class WinGame extends Action {

    public WinGame(IGameElement myGameElement) {
        super(myGameElement);
    }

    @Override
    public void perform() {
        getGameElement().changed();
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("endGame");
        ((Observable) getGameElement()).notifyObservers(myList);
    }

}
