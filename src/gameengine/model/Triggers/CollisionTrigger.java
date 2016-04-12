package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public abstract class CollisionTrigger implements ITrigger {

    private Actor myMainActor;
    private Actor myCollisionActor;

    public CollisionTrigger(Actor mainActor, Actor collisionActor) {
        setMyMainActor(mainActor);
        setMyCollisionActor(collisionActor);
    }

    @Override
    public abstract String getMyKey();

    @Override
    public abstract boolean evaluate(Actor myActor);

    public String makeName(String collisionType) {
        return getMyMainActor().getMyName() + collisionType + getMyCollisionActor().getMyName();
    }

	public Actor getMyMainActor() {
		return myMainActor;
	}

	public void setMyMainActor(Actor myMainActor) {
		this.myMainActor = myMainActor;
	}

	public Actor getMyCollisionActor() {
		return myCollisionActor;
	}

	public void setMyCollisionActor(Actor myCollisionActor) {
		this.myCollisionActor = myCollisionActor;
	}
}
