package gameengine.model.Actions;

import gameengine.model.Actor;

/**
 * This action moves the actor upwards with a force. 
 * Combined with ApplyPhysics, this results in a jumping action.
 * @author blakekaplan
 */
public class MoveUp extends MovingAction {

	public MoveUp(Actor actor) {
		super(actor);
	}

    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().moveUp(getMyActor());
    }


}
