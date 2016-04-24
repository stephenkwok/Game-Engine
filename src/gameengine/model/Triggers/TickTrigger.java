package gameengine.model.Triggers;


import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class TickTrigger extends ITrigger {

    private static final String TICK = "Tick";
    private int myInterval;

    public TickTrigger(){
        myInterval = 1;
    }

    public TickTrigger(int interval){
        myInterval = interval;
    }

    public int getMyInterval(){
        return myInterval;
    }

    @Override
    public String getMyKey() {
        return TICK;
    }

    /**
     * Checks a boolean condition against the state of an actor
     *
     * @param otherTrigger A trigger to check information against
     * @return A boolean that says if the condition is true or false
     */
    @Override
    public boolean evaluate(ITrigger otherTrigger) {
        TickTrigger otherTick = (TickTrigger) otherTrigger;
        if (otherTick.getMyInterval() % myInterval == 0) return true;
        return false;
    }
}