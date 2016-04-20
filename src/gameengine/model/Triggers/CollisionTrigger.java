package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    private IPlayActor myMainActor;
    private IPlayActor myCollisionActor;

    @Override
    public abstract boolean evaluate(ITrigger otherTrigger);

    public CollisionTrigger(IPlayActor actor1, IPlayActor actor2) {
        myMainActor = actor1;
        myCollisionActor = actor2;
    }

    @Override
    public abstract String getMyKey();

    public IPlayActor getMyMainActor() {
        return myMainActor;
    }

    public void setMyMainActor(IPlayActor myMainActor) {
        this.myMainActor = myMainActor;
    }

    public IPlayActor getMyCollisionActor() {
        return myCollisionActor;
    }

    public void setMyCollisionActor(Actor myCollisionActor) {
        this.myCollisionActor = myCollisionActor;
    }

    @Override
    public boolean equals(Object o) {
        CollisionTrigger otherTrigger = (CollisionTrigger) o;
        if (myMainActor.getName().equals(otherTrigger.getMyMainActor().getName()) && myCollisionActor.getName().equals(otherTrigger.getMyCollisionActor().getName())) {
            return true;
        }
        return false;
    }
}
