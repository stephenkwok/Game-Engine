package gameengine.model.Triggers;


import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class TickTrigger implements ITrigger {

    private static final String TICK = "Tick";

    @Override
    public String getMyKey() {
        return TICK;
    }

    @Override
    public boolean evaluate(IPlayActor myActor) {
        return true;

    }
}