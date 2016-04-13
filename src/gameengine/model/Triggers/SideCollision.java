package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.ITrigger;

public class SideCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_NAME = "SideCollision";

    public SideCollision(Actor mainActor, Actor collidesWith) {
        super(mainActor, collidesWith);
    }

    @Override
    public boolean evaluate(IActor myActor) {
        return true;
    }

    @Override
    public String getMyKey() {
        return makeName(COLLISION_NAME);
    }

}