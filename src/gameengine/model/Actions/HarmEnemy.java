package gameengine.model.Actions;

import gameengine.model.Actions.Action;
import gameengine.model.Actor;

public class HarmEnemy extends Action {

	private int damage = 1;
	
	public HarmEnemy(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setHealth(getActor().getHealth()-damage);
	}

	@Override
	public void performOn(Actor a) {
		//nothing happens to character
	}

}
