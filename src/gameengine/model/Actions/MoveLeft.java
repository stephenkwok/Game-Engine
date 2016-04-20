	package gameengine.model.Actions;


import gameengine.model.Actions.MovingAction;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveLeft extends MovingAction {

    private static final double LEFT_DIRECTION = 180;

    public MoveLeft(IPlayActor actor) {
        super(actor);
    }

    @Override

    public void perform() {
        getMyActor().getPhysicsEngine().moveLeft(getMyActor());
    }
}
