package gameengine.model;

import gameengine.actors.Actor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class ClickTrigger implements ITrigger {

    private double myX;
    private double myY;

    @Override
    public boolean evaluate(Actor myActor) {
        return false;
    }

    @Override
    public String getTriggerName() {
        return "Click";
    }

    public void setLocation(double x, double y) {
        myX = x;
        myY = y;
    }

}
