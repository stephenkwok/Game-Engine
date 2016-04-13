package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.ITrigger;

/**
 * @author blakekaplan
 */
public class ClickTrigger implements ITrigger {

    private double myX;
    private double myY;

    @Override
    public boolean evaluate(IActor myActor) {
        return myActor.getImageView().contains(getMyX(), getMyY());
    }

    @Override
    public String getMyKey() {
        return "Click";
    }

    public void setClickedAt(double x, double y) {
        setMyX(x);
        setMyY(y);
    }

	public double getMyX() {
		return myX;
	}

	public void setMyX(double myX) {
		this.myX = myX;
	}

	public double getMyY() {
		return myY;
	}

	public void setMyY(double myY) {
		this.myY = myY;
	}
}
