package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;

public abstract class CollisionTrigger extends ITrigger {

	private IPlayActor myMainActor;
	private IPlayActor myCollisionActor;

	public CollisionTrigger(Actor actor1, Actor actor2) {
		myMainActor = actor1;
		myCollisionActor = actor2;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{myMainActor,myCollisionActor};
	}
	
	@Override
	public abstract boolean evaluate(ITrigger otherTrigger);

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
		if (myMainActor.getID() == otherTrigger.getMyMainActor().getID()
				&& myCollisionActor.getID() == otherTrigger.getMyCollisionActor().getID()) {
			return true;
		}
		return false;
	}
}
