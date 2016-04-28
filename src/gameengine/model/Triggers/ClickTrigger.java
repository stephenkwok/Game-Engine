package gameengine.model.Triggers;

import gameengine.model.*;
import javafx.geometry.Bounds;

/**
 * @author blakekaplan
 */
public class ClickTrigger extends ITrigger {

    private Double myX;
    private Double myY;
    private IGameElement myGameElement;

    public ClickTrigger(IGameElement gameElement) {
        myGameElement = gameElement;
    }

    public ClickTrigger(Double x, Double y) {
        myX = x;
        myY = y;
    }
    
    public Object[] getParameters(){
    	if(myGameElement!=null){
    		return new Object[]{myGameElement};
    	}
		return new Object[]{myX,myY};
    }

    @Override
    public String getMyKey() {
        return "Click";
    }

    public double getMyX() {
        return myX;
    }

    public double getMyY() {
        return myY;
    }

    /**
     * Checks a boolean condition against the state of an actor
     *
     * @param otherTrigger A trigger to check information against
     * @return A boolean that says if the condition is true or false
     */
    @Override
    public boolean evaluate(ITrigger otherTrigger) {
        ClickTrigger otherClick = (ClickTrigger) otherTrigger;
        return (myGameElement.getBounds().contains(otherClick.getMyX(), otherClick.getMyY()));
    }

}
