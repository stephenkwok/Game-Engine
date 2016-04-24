package gameengine.model.Actions;

import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class MoveUp extends MovingAction {

    public MoveUp(IPlayActor actor) {
        super(actor);
    }

    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().moveUp(getMyActor());
    	getMyActor().setHeading(90);
    }

    
}
