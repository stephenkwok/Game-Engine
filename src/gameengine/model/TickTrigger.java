package gameengine.model;

import gameengine.controller.Actor;

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
