package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class BottomCollision extends CollisionTrigger implements ITrigger{

	public BottomCollision() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTriggerName() {
		return "BottomCollision";
	}

	@Override
	public boolean evaluate(Actor myActor) {
		// TODO Auto-generated method stub
		return false;
	}

}
