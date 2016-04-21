package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.ActorState;

/**
 * @author blakekaplan
 */
public class Destroy extends Action {

    public Destroy(Actor assignedActor){
        super(assignedActor);
    }

    @Override
    public void perform() {
    	System.out.println("blahaahhaaHAHAHAH");
        getMyActor().addState(ActorState.DEAD);
    }

}
