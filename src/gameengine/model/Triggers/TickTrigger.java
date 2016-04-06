package gameengine.model.Triggers;


import gameengine.model.Actor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class TickTrigger implements ITrigger {

    private static final String TICK = "Tick";

    @Override
    public String getTriggerName() {
        return TICK;
    }
/*
    @Override
    public boolean evaluate(Actor myActor) {
        return true;
    }*/

	@Override
	public boolean evaluate(Actor myActor) {
		// TODO Auto-generated method stub
		return false;
	}
}
