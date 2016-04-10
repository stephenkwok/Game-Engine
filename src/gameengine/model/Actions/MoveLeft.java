package gameengine.model.Actions;


import gameengine.model.Actions.MovingAction;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveLeft extends MovingAction {

    private static final double LEFT_DIRECTION = 180;

    public MoveLeft(Actor assignedActor) {
        super(assignedActor);
    }

    @Override

    public void perform() {
        getActor().getPhysicsEngine().moveLeft(getActor());
    }
}
