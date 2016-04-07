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
	Map<String,Double> actorCorners;
	private String LEFT   =  "left";
	private String RIGHT  =  "right";
	private String TOP    =  "top";
	private String BOTTOM =  "bottom";
	
	
	
	public CollisionDetection( PhysicsEngine physicsEngine){
		myPhysicsEngine = physicsEngine;
	}
	
	public List<Actor> detection(List<Actor> actors){
		for (Actor a1 : actors){
			for(Actor a2 : actors){
				if(checkBoundingBoxes(a1,a2)){
					resolveCollision(a1,a2);
				}
			}
		}
		return actors;
	}
	
	private boolean checkBoundingBoxes(Actor a1, Actor a2) {
		Map<String,Double> a1Corners = getCorners(a1);
		Map<String,Double> a2Corners = getCorners(a2);
		return isCollision(a1Corners,a2Corners);
		}
	
	private Map<String, Double> getCorners(Actor a){ //This may have to change if Actor is no longer an ImageView object
		Map<String,Double> actorMap = new HashMap<String,Double>();
		actorMap.put(LEFT  , a.getX());
		actorMap.put(RIGHT , a.getX()+a.getImage().getWidth());
		actorMap.put(TOP   , a.getY());
		actorMap.put(BOTTOM, a.getY()+a.getImage().getHeight());
		return actorMap;
	}
	
	private boolean isCollision(Map<String,Double> actorMap1, Map<String,Double> actorMap2){
		if(  
				edgeCollision(actorMap1,actorMap2,LEFT, RIGHT, LEFT) ||
				edgeCollision(actorMap1,actorMap2,LEFT, RIGHT, RIGHT) ||
				edgeCollision(actorMap1,actorMap2,BOTTOM, TOP, BOTTOM) ||
				edgeCollision(actorMap1,actorMap2,BOTTOM, TOP, TOP) 
		){
			return true;
		}
		return false;
	}

	private CollisionTrigger getCollisionType(Map<String,Double> a1Map, Map<String,Double> a2Map){
		CollisionTrigger ct = null;
		if( edgeCollision(a1Map,a2Map,LEFT, RIGHT, LEFT) ||
			edgeCollision(a1Map,a2Map,LEFT, RIGHT, RIGHT) ){
			ct = new SideCollision();
		}
		else if(edgeCollision(a1Map,a2Map,BOTTOM, TOP, BOTTOM)){
			ct = new TopCollision();
		}
		else if(edgeCollision(a1Map,a2Map,BOTTOM, TOP, TOP)){
			ct = new BottomCollision();
		}
		return ct;
	}
	private boolean edgeCollision(Map<String,Double> a1Map, Map<String,Double> a2Map,String edge1, String edge2, String a2Edge ) {
		return ( (a1Map.get(edge1)   <= a2Map.get(a2Edge))   &&  (a2Map.get(a2Edge)   <= a1Map.get(edge2)) );
	}

	private void resolveCollision(Actor a1, Actor a2){
		String collisionType = getCollisionType(getCorners(a1),getCorners(a2)).getTriggerName();
		String triggerString = a1.getName() + collisionType + a2.getName();
		a1.performActionsFor(myPhysicsEngine,triggerString);   //Needs to be changed to take a string parameter
	}	
}
