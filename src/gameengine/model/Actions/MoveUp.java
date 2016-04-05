package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.controller.Action;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveUp extends MovingAction {

    private static final double UP_ANGLE = 90.0;

    public MoveUp(Actor assignedActor, List<Object> args) {
        super(assignedActor, args);
    }

    @Override
    public void perform() {
        moveActor(UP_ANGLE);
    }
}
