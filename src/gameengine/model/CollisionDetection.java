package gameengine.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gameengine.model.Actor;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.CollisionTrigger;
import gameengine.model.Triggers.SideCollision;
import gameengine.model.Triggers.TopCollision;;

/**
 * Collision Detection class handles checking for collisions among a list of Actors
 * It also handles resolving said collision should one be found
 * 
 * @author justinbergkamp
 *
 */
public class CollisionDetection {

	PhysicsEngine myPhysicsEngine;

	
	
	
	public CollisionDetection( PhysicsEngine physicsEngine){
		myPhysicsEngine = physicsEngine;
	}
	
	/**
	 * Called on list of actors in Level to detect any collisions between unique actors
	 * @return List of actors with updated position variables
	 */
	public List<Actor> detection(List<Actor> actors){
		for (Actor a1 : actors){
			for(Actor a2 : actors){
				if(a1 != a2 ){            //Checks that each actor in the pair is unique
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
	//Should this be a String? Just using Magic Strings for now
	private String getCollisionType(Actor a1, Actor a2){
		double xOverlap = 0;
		double yOverlap = 0;
		if(a1.getBounds().getMaxX() <= a2.getBounds().getMaxX()){
			xOverlap = a1.getBounds().getMaxX() -  a2.getXPos();
		}else{
			xOverlap = a2.getBounds().getMaxX() -  a1.getXPos();
		}
		
		if(a1.getBounds().getMaxY() <= a2.getBounds().getMaxY()){
			yOverlap = a1.getBounds().getMaxY() -  a2.getYPos();
		}else{
			yOverlap = a2.getBounds().getMaxY() -  a1.getYPos();
		}
				
		if(xOverlap <= yOverlap){
			return "SideCollision";
		}else{   
			return "TopCollision";
		}
	}
	
	private void resolveCollision(Actor a1, Actor a2){
		String collisionType = getCollisionType(a1,a2);
		String triggerString = a1.getName() + collisionType + a2.getName();
		a1.performActionsFor(triggerString);   //Needs to be changed to take a string parameter
	}	
}
