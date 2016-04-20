package gameengine.model.Triggers;

import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

public class BottomCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_TYPE = "BottomCollision";

    public BottomCollision(IPlayActor actor1, IPlayActor actor2) {
        super(actor1, actor2);
    }


    @Override
    public String getMyKey() {
        return makeName(COLLISION_TYPE);
    }

	@Override
	public boolean evaluate(IPlayActor myActor) {
		return true;
	}

}