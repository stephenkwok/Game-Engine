package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * @author blakekaplan
 */
public class MoveUp extends MovingAction {

	public MoveUp(Actor actor) {
		super(actor);
	}

    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().moveUp(getMyActor());
    	//getMyActor().setHeading(90);
    }


}
