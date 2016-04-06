package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;

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
