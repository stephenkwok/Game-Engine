package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;
import gameplayer.controller.GameController;

/**
 * @author blakekaplan
 */
public class Stop extends ControllerAction {

    public Stop(Actor assignedActor, GameController controller){
        super(assignedActor, controller);
    }

    @Override
    public void perform(PhysicsEngine myPhysicsEngine) {
        stop();
    }

}
