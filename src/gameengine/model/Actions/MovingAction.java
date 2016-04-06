package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;

import java.util.List;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends Action {

    private double distance;

    public MovingAction(Actor assignedActor, List<Object> args){
        super(assignedActor);
        distance = (Double) args.get(0);
    }

    public void moveActor(double angle){
        getActor().move(distance, angle);
    }

    @Override
    public abstract void perform();
}