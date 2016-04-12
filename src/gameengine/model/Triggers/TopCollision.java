package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class TopCollision extends CollisionTrigger implements ITrigger {

    private static final String COLLISION_TYPE = "TopTrigger";

    public TopCollision(Actor mainActor, Actor collisionActor) {
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