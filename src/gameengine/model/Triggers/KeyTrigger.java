package gameengine.model.Triggers;

import gameengine.actors.Actor;
import gameengine.model.ITrigger;

public abstract class KeyTrigger implements ITrigger {

	@Override
	public abstract String getTriggerName();

	@Override
	public boolean evaluate(Actor myActor) {
		return true;
	}

}
