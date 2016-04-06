package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class SideCollision implements ITrigger {

	public SideCollision() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTriggerName() {
		return "SideCollision";
	}

	@Override
	public boolean evaluate(Actor myActor) {
		return false;
	}

}
