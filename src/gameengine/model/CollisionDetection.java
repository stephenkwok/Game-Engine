package gameengine.model;

import java.util.ArrayList;
import java.util.List;
import gameengine.model.Actor;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.CollisionTrigger;
import gameengine.model.Triggers.NeutralCollision;
import gameengine.model.Triggers.SideCollision;
import gameengine.model.Triggers.TopCollision;;

/**
 * Collision Detection class handles checking for collisions among a list of Actors
 * It also handles resolving said collision should one be found
 * Needs Refactoring
 * 
 * @author justinbergkamp
 *
 */
public class CollisionDetection {

	public void detection(List<Actor> actors){
		for (Actor a1 : actors){
			for(Actor a2 : actors){
				if(checkBoundingBoxes(a1,a2)){
					resolveCollision(a1,a2);
				}
			}
		}
	}
	
	private boolean checkBoundingBoxes(Actor a1, Actor a2) {
		return isCollision(getCorners(a1), getCorners(a2));
		}

	private CollisionTrigger getCollisionType(Actor a1, Actor a2){
		CollisionTrigger trigger ;
		if(checkEdges(getCorners(a1), getCorners(a2),2,2)){
			trigger = new TopCollision();
		}
		if(checkEdges(getCorners(a1), getCorners(a2),2,3)){
			trigger = new BottomCollision();
		}
		else{
			trigger = new SideCollision();
		}
		return trigger;
	}
	private void resolveCollision(Actor a1, Actor a2){
		if(a1.getStrength() < a2.getStrength()){
			a2.collidesWith(a1,getCollisionType( a1,  a2));
			
		}else if(a1.getStrength() > a2.getStrength()){
			a1.collidesWith(a2,getCollisionType( a1, a2));
		}else{
			NeutralCollision nc = new NeutralCollision();
			a1.performActionsFor(nc);
			a2.performActionsFor(nc);
		}
	}
	
	private List<Double> getCorners(Actor a){
		List<Double> positions = new ArrayList<Double>();
		//left,right,top, bottom
		positions.add(a.getX());
		positions.add(a.getX()+a.getImage().getWidth());
		positions.add(a.getY());
		positions.add(a.getY()+a.getImage().getHeight());
		return positions;
	}
	
	private boolean isCollision(List<Double> actor1 ,List<Double> actor2){
		boolean horizontal = false;
		boolean vertical   = false; 
		//left
		if( checkEdges(actor1, actor2,0,0) ){
			horizontal = true;
		}
		//right
		if (checkEdges(actor1, actor2,0,1)){
			horizontal = true;
		}
		//top
		if (checkEdges(actor1, actor2,2,2)){
			vertical = true;
		}
		//bottom
		if (checkEdges(actor1, actor2,2,3)){
			vertical = true;
		}	
		return (vertical & horizontal);
	}
	
	private boolean checkEdges(List<Double> actor1 ,List<Double> actor2, int e, int d){
		return (actor1.get(e) <= actor2.get(d) & actor2.get(d) <= actor1.get(e+1));
	}
	
}
