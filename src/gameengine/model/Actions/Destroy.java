package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class Destroy extends ActorAction {

	public Destroy(Actor assignedActor, Boolean oneTime) {
		super(assignedActor, oneTime);
	}

	@Override
	public void execute() {
		getMyActor().addState(ActorState.DEAD);
	}

}
