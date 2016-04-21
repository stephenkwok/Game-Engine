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
	void createRuleTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((int) getValue());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		addTrigger(this, myTrigger);
		System.out.println(myTrigger);
	}
}
