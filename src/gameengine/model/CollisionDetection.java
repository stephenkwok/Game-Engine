package gameengine.model;

import java.util.List;

import gameengine.actors.Actor;

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
		return true;
	}

	private void resolveCollision(Actor a1, Actor a2){
		if(a1.getStrength() < a2.getStrength()){
			a2.collidesWith(a1);
			
		}else if(a1.getStrength() > a2.getStrength()){
			a1.collidesWith(a2);
		}else{
			a1.performActionsFor(ReverseVelocity);
			a2.performActionsFor(ReverseVelocity);
		}
	}
	
}
