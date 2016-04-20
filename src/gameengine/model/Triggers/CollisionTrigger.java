package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    private IActor myMainActor;
    private IActor myCollisionActor;

    @Override
    public abstract boolean evaluate(ITrigger otherTrigger);

    public CollisionTrigger(Actor mainActor, Actor collisionActor) {
        setMyMainActor(mainActor);
        setMyCollisionActor(collisionActor);
    }

    @Override
    public abstract String getMyKey();

    public IActor getMyMainActor() {
        return myMainActor;
    }

    public void setMyMainActor(IActor myMainActor) {
        this.myMainActor = myMainActor;
    }

    public IActor getMyCollisionActor() {
        return myCollisionActor;
    }

    public void setMyCollisionActor(Actor myCollisionActor) {
        this.myCollisionActor = myCollisionActor;
    }

    @Override
    public boolean equals(Object o) {
        CollisionTrigger otherTrigger = (CollisionTrigger) o;
        if (myMainActor.getMyName().equals(otherTrigger.getMyMainActor().getMyName()) && myCollisionActor.getMyName().equals(otherTrigger.getMyCollisionActor().getMyName())) {
            return true;
        }
        return false;
    }
}
