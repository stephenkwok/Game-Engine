package gameengine.model.Actions;

import gameengine.model.ActorState;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class Destroy extends ActorAction {

	public Destroy(IGameElement assignedActor) {
		super((IPlayActor) assignedActor);
	}

	@Override
	public void perform() {
		getMyActor().addState(ActorState.DEAD);
	}

}
