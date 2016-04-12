package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class BottomCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_TYPE = "BottomCollision";

    public BottomCollision(Actor mainActor, Actor collisionActor) {
        super(mainActor, collisionActor);
    }

    @Override
    public String getMyKey() {
        return makeName(COLLISION_TYPE);
    }

    @Override
    public boolean evaluate(Actor myActor) {
        return true;
    }

}
