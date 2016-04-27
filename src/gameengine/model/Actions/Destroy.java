package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

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
