package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.ITrigger;

public class BottomCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_TYPE = "BottomCollision";

    public BottomCollision(Actor mainActor, Actor collisionActor) {
        super(mainActor, collisionActor);
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