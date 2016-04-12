package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;
import gameengine.model.PhysicsEngine;

public class HarmEnemy extends Action {

	private int damage = 1;
	
	public HarmEnemy(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
