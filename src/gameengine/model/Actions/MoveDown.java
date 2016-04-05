package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.controller.Action;

import java.util.List;

/**
 * @author blakekaplan
 */
public class MoveDown extends MovingAction {

    private static final int DOWN_ANGLE = 270;

    public MoveDown(Actor assignedActor, List<Object> args) {
        super(assignedActor, args);
    }

    @Override
    public void perform() {
        moveActor(DOWN_ANGLE);
    }
}
