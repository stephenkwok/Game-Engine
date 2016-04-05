package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameplayer.controller.GameController;

/**
 * @author blakekaplan
 */
public class Start extends ControllerAction {

    public Start(Actor assignedActor, GameController controller) {
        super(assignedActor, controller);
    }

    @Override
    public void perform() {
        start();
    }
}
