package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.model.IAction;
import gameengine.model.ITrigger;

public class TickBehavior extends DoubleBehavior {
	private ITrigger myTrigger;
	
	public TickBehavior(String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
	}

	@Override
	void createRuleTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((int) getValue());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		System.out.println(myTrigger);
	}

	@Override
	public IAction getAction() {
		return null;
	}

	@Override
	public ITrigger getTrigger() {
		return this.myTrigger;
	}

}
