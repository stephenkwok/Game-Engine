package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.Actions.Action;
import gameengine.model.PhysicsEngine;

import java.util.List;

/**
 * @author blakekaplan
 */
public abstract class MovingAction extends Action {

    public MovingAction(Actor assignedActor){
        super(assignedActor);
    }

    @Override
    public abstract void perform();
}
