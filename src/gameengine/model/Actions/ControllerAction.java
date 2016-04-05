package gameengine.model.Actions;

import gameengine.actors.Actor;
import gameengine.controller.Action;
import gameengine.controller.Game;
import gameplayer.controller.GameController;

/**
 * @author blakekaplan
 */
public abstract class ControllerAction extends Action {

    private GameController myController;

    public ControllerAction(Actor assignedActor, GameController controller) {
        super(assignedActor);
        myController = controller;
    }

    public void start() {
        myController.begin();
    }

    public void stop() {
        myController.endGame();
    }

    @Override
    public abstract void perform();
}
