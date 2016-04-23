package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.AttributeType;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;

public class ChangeAttributeBehavior extends DoubleBehavior {
	private static final String CHANGE_HEALTH = "ChangeHealth";
	private IAuthoringActor myActor;
	private IAction myAction;
	
	public ChangeAttributeBehavior(IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	void createRuleTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		if(getBehaviorType().equals(CHANGE_HEALTH)) arguments.add(AttributeType.HEALTH);
		else arguments.add(AttributeType.POINTS);
		arguments.add((int) getValue());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		System.out.println(myAction);
	}

	@Override
	public IAction getAction() {
		return myAction;
	}

	@Override
	public ITrigger getTrigger() {
		return null;
	}

}
