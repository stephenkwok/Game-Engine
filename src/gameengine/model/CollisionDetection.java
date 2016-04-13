package gameengine.model;

import java.util.List;
import gameengine.model.Actor;


/**
 * Collision Detection class handles checking for collisions among a list of Actors
 * It also handles resolving said collision should one be found
 * 
 * @author justinbergkamp
 *
 */
public class CollisionDetection {
	
	private String SideCollision = "SideCollision";
	private String TopCollision = "TopCollision";
	private String BottomCollision = "BottomCollision";
	
	private PhysicsEngine myPhysicsEngine;
	
	public CollisionDetection( PhysicsEngine physicsEngine){
		setMyPhysicsEngine(physicsEngine);
	}
	
	/**
	 * Called on list of actors in Level to detect any collisions between unique actors
	 * @return List of actors with updated position variables
	 */
	public List<Actor> detection(List<Actor> actors){
		for (Actor a1 : actors){
			a1.setInAir(true);
			for(Actor a2 : actors){
				if(a1 != a2){            //Checks that each actor in the pair is unique
					if(isCollision(a1,a2))
						resolveCollision(a1,a2);
				}
			}
		}
		return actors;
	}
		
	/**
	 * Determines if a collision is occurring by checking for intersecting Bounds. 
	 * 
	 * @param a1
	 * @param a2
	 * @return True = Is Collision, False = No Collision
	 */
	private boolean isCollision(Actor a1, Actor a2){
		return a1.getBounds().intersects(a2.getBounds());
	}
	
	/**
	 * Determines which type of Collision is occurring:
	 * Side/Top/Bottom
	 * Method does so by checking if a pair of actors overlaps more along the horizontal or vertical axis
	 * @param a1
	 * @param a2
	 * @return Type of collision-String
	 */
	private String getCollisionType(Actor a1, Actor a2){

		
		double w = (0.5 * (a1.getBounds().getWidth() + a2.getBounds().getWidth()));
		double h = (0.5 * (a1.getBounds().getHeight() + a2.getBounds().getHeight()));
		double dx = ((a1.getBounds().getMaxX()-a1.getBounds().getMinX())/2- (a2.getBounds().getMaxX()-a2.getBounds().getMinX())/2);
		double dy = ((a1.getBounds().getMaxY()-a1.getBounds().getMinX())/2 - (a2.getBounds().getMaxY()-a2.getBounds().getMinX())/2);



		double wy = w * dy;
		double hx = h * dx;

		if (wy > hx) {
			if (wy > -hx) {
				return "TopCollision";
			} else {
				return "SideCollision";
			}
		} else {
			if (wy >= -hx) {
				return "SideCollision";
			} else {
				return "BottomCollision";
			}
		} 

	}
	
	private void resolveCollision(Actor a1, Actor a2){
		String collisionType = getCollisionType(a1,a2);
		String triggerString = a1.getMyName() + collisionType + a2.getMyName();
		a1.performActionsFor(triggerString);   
	}

	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}	
}