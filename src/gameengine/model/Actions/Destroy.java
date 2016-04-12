package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class Destroy extends Action {

    public Destroy(Actor assignedActor){
        super(assignedActor);
    }

    @Override
    public void perform() {
        getMyActor().setDead(true);
    }

}
