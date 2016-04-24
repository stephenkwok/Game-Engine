package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.AttributeType;
import gameengine.model.IAction;
import gameengine.model.IPlayActor;

public class ChangeAttributeBehavior extends DoubleBehavior {
	private static final String CHANGE_HEALTH = "ChangeHealth";
	private IAuthoringActor myActor;
	private IAction myAction;
	
	public ChangeAttributeBehavior(ActorRule myActorRule, IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((IPlayActor) myActor);
		if(getBehaviorType().equals(CHANGE_HEALTH)) arguments.add(AttributeType.HEALTH);
		else arguments.add(AttributeType.POINTS);
		arguments.add((int) getValue());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		setTriggerOrAction();
	}

	@Override
	public boolean isTrigger() {
		return false;
	}
}
