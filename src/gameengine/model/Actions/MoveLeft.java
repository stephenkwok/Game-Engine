package gameengine.model.Actions;

import gameengine.controller.Action;
import gameengine.model.Actor;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveLeft extends MovingAction {

    private static final double LEFT_DIRECTION = 180;

    public MoveLeft(Actor assignedActor, List<Object> args) {
        super(assignedActor, args);
    }

    @Override
    public void perform() {
        moveActor(LEFT_DIRECTION);
    }

}
