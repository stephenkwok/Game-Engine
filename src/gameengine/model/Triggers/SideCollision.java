package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class SideCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_NAME = "SideCollision";

    public SideCollision(Actor mainActor, Actor collidesWith) {
        super(mainActor, collidesWith);
    }

    @Override
    public String getTriggerName() {

        return getMainActorName() + COLLISION_NAME + getCollisionActorName();
    }

    @Override
    public boolean evaluate(Actor myActor) {
        return true;
    }

}
