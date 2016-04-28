package gameengine.model.Actions;


import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;


public class Jump extends MovingAction {

	public Jump(Actor actor) {
		super(actor);
	}

    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().jump(getMyActor());
    	//getMyActor().setHeading(90);
    }


}
