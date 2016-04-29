package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

/**
 * An example of an Action to glide an Actor right by a given distance (no
 * gravity, no friction).
 *
 * @author michelle
 */
public class GlideTarget extends GlidingAction {
	
    /**
     * Takes in reference to the Actor it will change 
     * 
     * @param assignedActor The Actor that will be changed
     */
	Actor targetActor;
	public GlideTarget(Actor assignedActor, Double offset, Actor target) {
		super(assignedActor, offset);
		targetActor = target;
	}

	/**
	 * Moves the Actor to the right through gliding
	 */
	@Override
	public void perform() {
		calcHeading((Actor)getMyActor(),targetActor);
    	getMyActor().getPhysicsEngine().glideRight(getMyActor(),this.getGlideOffset());		

	}
	
	public void calcHeading(Actor assignedActor, Actor target){
		double verticalDiff   = assignedActor.getY()  - target.getY();
		double horizontalDiff  = target.getX()  - assignedActor.getX();
		double angle  = Math.toDegrees(Math.sin(Math.toRadians(verticalDiff/horizontalDiff)));
		//double newX  = Math.toDegrees(Math.cos(Math.toRadians(verticalDiff/horizontalDiff)));

		if(verticalDiff > 0 && horizontalDiff > 0){
			assignedActor.setHeading(angle);
			
		}else if(verticalDiff <= 0 && horizontalDiff <= 0){
			assignedActor.setHeading(180+angle);
			
		}
		else if(verticalDiff <= 0){
			assignedActor.setHeading(360-angle);
			
		}
		else if( horizontalDiff <= 0){
			assignedActor.setHeading(180-angle);
			
		}
	}

}
