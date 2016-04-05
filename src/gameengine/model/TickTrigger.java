package gameengine.model;

import gameengine.actors.Actor;

/**
 * @author blakekaplan
 */
public class TickTrigger implements ITrigger {

    private static final String TICK = "Tick";

    @Override
    public String getTriggerName() {
        return TICK;
    }

    @Override
    public boolean evaluate(Actor myActor) {
        return true;
    }
}
