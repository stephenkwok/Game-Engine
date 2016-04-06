package gameengine.model.Actions;

import gameengine.model.Actor;
import gameplayer.controller.GameController;

/**
 * @author blakekaplan
 */
public class Stop extends ControllerAction {

    public Stop(Actor assignedActor, GameController controller){
        super(assignedActor, controller);
    }

    @Override
    public void perform() {
        stop();
    }

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}
}
