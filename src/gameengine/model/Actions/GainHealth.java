package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.controller.Action;

public class GainHealth extends Action{

	private int numLivesGained = 1;
	
	public GainHealth(Actor assignedActor) {
		super(assignedActor);
	}

	@Override
	public void perform() {
		getActor().setHealth( getActor().getHealth()+numLivesGained );
	}

	@Override
	public void performOn(Actor a) {
		// TODO Auto-generated method stub
		
	}

}
