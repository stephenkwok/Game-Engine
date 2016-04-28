package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.ActorState;

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
		
		if(!targetActor.checkState(ActorState.DEAD)){
			calcHeading((Actor)getMyActor(),targetActor);
		}		
    	getMyActor().getPhysicsEngine().glideForward(getMyActor(),this.getGlideOffset());		

	}
	
	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),this.getGlideOffset(), targetActor};
	}
	
	private void calcHeading(Actor assignedActor, Actor target){
		double verticalDiff   = assignedActor.getY()  - target.getY();
		//System.out.println("Y1 - Y2  "+ verticalDiff);
		double horizontalDiff  = target.getX()  - assignedActor.getX();
		//System.out.println("X2 - X1  "+ horizontalDiff);
		//System.out.println(Math.sin((verticalDiff/horizontalDiff)));
		double angle  = Math.toDegrees(Math.sin((verticalDiff/horizontalDiff)));
		//System.out.println("First Angle " + angle);
		//double newX  = Math.toDegrees(Math.cos(Math.toRadians(verticalDiff/horizontalDiff)));

		if(verticalDiff > 0 && horizontalDiff > 0){
			assignedActor.setHeading(angle);
			
		}else if(verticalDiff <= 0 && horizontalDiff <= 0){
			assignedActor.setHeading(180+angle);
			
		}
		else if(verticalDiff <= 0 && horizontalDiff > 0){
			assignedActor.setHeading(360+angle);
			
		}
		else if( horizontalDiff <= 0 && verticalDiff > 0){
			assignedActor.setHeading(180+angle);
			
		}
	}

}
