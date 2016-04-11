package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    Actor myMainActor;
    Actor myCollisionActor;

    public CollisionTrigger(Actor mainActor, Actor collisionActor) {
        myMainActor = mainActor;
        myCollisionActor = collisionActor;
    }

    @Override
    public abstract String getTriggerName();

    @Override
    public abstract boolean evaluate(Actor myActor);

    public String makeName(String collisionType) {
        return myMainActor.getName() + collisionType + myCollisionActor.getName();
    }
}
