package gameengine.model.Triggers;

import gameengine.model.Actor;

public class BottomCollision extends CollisionTrigger {

	private static final String COLLISION_TYPE = "BottomCollision";

	public BottomCollision(Actor actor1, Actor actor2, Boolean oneTime) {
		super(actor1, actor2, oneTime);
	}
	
	public BottomCollision(Actor actor1, Actor actor2) {
		super(actor1, actor2, false);
	}

	@Override
	public boolean evaluateCollision(ITrigger otherTrigger) {
		BottomCollision otherCollision = (BottomCollision) otherTrigger;
		return this.equals(otherCollision);
	}

	@Override
	public String getMyKey() {
		return COLLISION_TYPE;
	}
}