package gameengine.model.Triggers;

import gameengine.model.Actor;

public class NeutralCollision extends CollisionTrigger {

    private static final String COLLISION_TYPE = "NeutralCollision";

    public NeutralCollision(Actor mainActor, Actor collisionActor) {
        super(mainActor, collisionActor);
    }

    @Override
    public String getTriggerName() {
        return makeName(COLLISION_TYPE);
    }

    @Override
    public boolean evaluate(Actor myActor) {
        return true;
    }
}
