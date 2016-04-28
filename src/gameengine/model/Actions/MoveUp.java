package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class MoveUp extends MovingAction {

	public MoveUp(Actor actor, Boolean oneTime) {
		super(actor, oneTime);
	}

    @Override
    public void execute() {
    	getMyActor().getPhysicsEngine().moveUp(getMyActor());
    	//getMyActor().setHeading(90);
    }


}
