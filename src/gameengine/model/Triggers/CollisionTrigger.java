package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    private IPlayActor myMainActor;
    private IPlayActor myCollisionActor;

    @Override
    public abstract boolean evaluate(IPlayActor myActor);

    public CollisionTrigger(IPlayActor actor1, IPlayActor actor2) {
        myMainActor = actor1;
        myCollisionActor = actor2;
    }

    @Override
    public abstract String getMyKey();

    public String makeName(String collisionType) {
        return getMyMainActor().getName() + collisionType + getMyCollisionActor().getName();
    }

	public IPlayActor getMyMainActor() {
		return myMainActor;
	}

	public IPlayActor getMyCollisionActor() {
		return myCollisionActor;
	}
}
