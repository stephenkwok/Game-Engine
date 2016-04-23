package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.view.ActorRule;
import gameengine.model.ITrigger;

public class TickBehavior extends DoubleBehavior {
	private ITrigger myTrigger;
	
	public TickBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
	}

	@Override
	public void setTriggerOrAction() {
		addTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((int) getValue());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return true;
	}
}
