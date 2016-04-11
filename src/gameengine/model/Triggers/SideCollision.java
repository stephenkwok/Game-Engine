package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class SideCollision extends CollisionTrigger implements ITrigger {
	private Actor a1;
	private Actor a2;
	
	public SideCollision(Actor a1, Actor a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	@Override
	public String getTriggerName() {
		return a1.getName()+"SideCollision"+a2.getName();
	}

	@Override
	public boolean evaluate(Actor myActor) {
		return false;
	}

}
