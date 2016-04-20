	package gameengine.model.Actions;


import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Actions.MovingAction;

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
        getMyActor().getPhysicsEngine().moveLeft(getMyActor());
        getMyActor().setHeading(180);
    }
}
