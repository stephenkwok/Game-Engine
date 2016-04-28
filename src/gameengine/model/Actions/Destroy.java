package gameengine.model.Actions;

import gameengine.model.*;

/**
 * @author blakekaplan
 */
public class Destroy extends ActorAction {

	public Destroy(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().addState(ActorState.DEAD);
	}

}
