package gameengine.model.Triggers;

import gameengine.model.IPlayActor;

public class BottomCollision extends CollisionTrigger {

    private static final String COLLISION_TYPE = "BottomCollision";

    public BottomCollision(IPlayActor actor1, IPlayActor actor2) {
        super(actor1, actor2);
    }

    @Override
    public boolean evaluate(ITrigger otherTrigger) {
        BottomCollision otherCollision = (BottomCollision) otherTrigger;
        return this.equals(otherCollision);
    }

    @Override
    public String getMyKey() {
        return COLLISION_TYPE;
    }
}