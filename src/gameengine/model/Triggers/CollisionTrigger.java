package gameengine.model.Triggers;

import java.util.ArrayList;
import java.util.List;

import gameengine.model.Actor;
import gameengine.model.IPlayActor;

public abstract class CollisionTrigger extends Trigger {

	private IPlayActor myMainActor;
	private IPlayActor myCollisionActor;
	private Boolean oneTime;
	private List<IPlayActor> resolvedCollisions;

	public CollisionTrigger(Actor actor1, Actor actor2, Boolean oneTime) {
		myMainActor = actor1;
		myCollisionActor = actor2;
		resolvedCollisions = new ArrayList<IPlayActor>();
		this.oneTime = oneTime;
	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{myMainActor,myCollisionActor, oneTime};
	}
	
	@Override
	public boolean evaluate(ITrigger otherTrigger){
		if(!resolvedCollisions.contains(((CollisionTrigger)otherTrigger).getMyCollisionActor())){
			if(oneTime){
				resolvedCollisions.add(((CollisionTrigger)otherTrigger).getMyCollisionActor());
			}
			return evaluateCollision(otherTrigger);
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
	
	public boolean isOneTime() {
		return oneTime;
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
	
	public boolean equals(CollisionTrigger other){
		return (myMainActor.getID() == other.getMyMainActor().getID() && myCollisionActor.getID() == other.getMyCollisionActor().getID());
	}
}