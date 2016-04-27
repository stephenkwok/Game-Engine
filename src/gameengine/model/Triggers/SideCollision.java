package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;

public class SideCollision extends CollisionTrigger {

	private static final String COLLISION_NAME = "SideCollision";

	public SideCollision(Actor actor1, Actor actor2) {
		super(actor1, actor2);
	}

	@Override
	public boolean evaluate(ITrigger otherTrigger) {
		SideCollision otherCollision = (SideCollision) otherTrigger;
		return this.equals(otherCollision);

	}

	@Override
	public String getMyKey() {
		return COLLISION_NAME;
	}
}