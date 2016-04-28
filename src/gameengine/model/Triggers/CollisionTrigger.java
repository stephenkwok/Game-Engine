package gameengine.model.Triggers;

import java.util.List;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;

public abstract class CollisionTrigger extends ITrigger {

	private IPlayActor myMainActor;
	private IPlayActor myCollisionActor;
	private Boolean oneTime;
	private List<IPlayActor> resolvedCollisions;

	public CollisionTrigger(Actor actor1, Actor actor2, Boolean oneTime) {
		myMainActor = actor1;
		myCollisionActor = actor2;
		this.oneTime = oneTime;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{myMainActor,myCollisionActor};
	}
	
	@Override
	public boolean evaluate(ITrigger otherTrigger){
		if(!resolvedCollisions.contains(myCollisionActor)){
			return evaluateCollision(otherTrigger);
		}
		if(oneTime){
			resolvedCollisions.add(myCollisionActor);
		}
		return false;
	}
	
	public abstract boolean evaluateCollision(ITrigger otherTrigger);
	
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
