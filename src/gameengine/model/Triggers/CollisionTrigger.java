package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    private IActor myMainActor;
    private IActor myCollisionActor;

    @Override
    public abstract boolean evaluate(IPlayActor myActor);

    public CollisionTrigger(Actor mainActor, Actor collisionActor) {
        setMyMainActor(mainActor);
        setMyCollisionActor(collisionActor);
    }

    @Override
    public abstract String getMyKey();

    public String makeName(String collisionType) {
        return getMyMainActor().getMyName() + collisionType + getMyCollisionActor().getMyName();
    }

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
}
