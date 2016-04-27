package gameengine.model.Actions;


import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;


public class Jump extends MovingAction {

	public Jump(IGameElement actor) {
		super(actor);
	}

    @Override
    public void perform() {
    	getMyActor().getPhysicsEngine().jump(getMyActor());
    	//getMyActor().setHeading(90);
    }


}
