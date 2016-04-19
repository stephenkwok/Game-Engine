package gameengine.model.Triggers;

import gameengine.model.*; 
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class ClickTrigger implements ITrigger {

    private double myX;
    private double myY;

    @Override
    public boolean evaluate(IPlayActor myActor) {
        return ((IDisplayActor) myActor).getMyImageView().contains(myX, myY);
    }

    @Override
    public String getMyKey() {
        return "Click";
    }

    public void setClickedAt(double x, double y) {
        myX = x;
        myY = y;
    }

}
