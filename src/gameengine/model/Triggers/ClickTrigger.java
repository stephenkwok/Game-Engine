package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class ClickTrigger implements ITrigger {

    private double myX;
    private double myY;

    @Override
    public boolean evaluate(Actor myActor) {
        return myActor.contains(myX, myY);
    }

    @Override
    public String getTriggerName() {
        return "Click";
    }

    public void setClickedAt(double x, double y) {
        myX = x;
        myY = y;
    }

}
