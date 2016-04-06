package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class TopCollision implements ITrigger{

	public TopCollision() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTriggerName() {
		return "TopCollision";
	}

	@Override
	public boolean evaluate(Actor myActor) {
		// TODO Auto-generated method stub
		return false;
	}

}
