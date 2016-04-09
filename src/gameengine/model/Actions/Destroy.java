package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class Destroy extends Action {

    public Destroy(Actor assginedActor){
        super(assginedActor);
    }

    @Override
    public void perform() {
        getActor().changed();
        getActor().notifyObservers();
    }

}
