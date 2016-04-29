package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.*;
import gameengine.model.Actions.ChangeAttribute;

public class ChangeAttributeBehavior extends DoubleBehavior {
	private static final String CHANGE_HEALTH = "ChangeHealth";
	private IAuthoringActor myActor;
	private IAction myAction;
	private AttributeType attributeType;

	public ChangeAttributeBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
		this.attributeType = AttributeType.POINTS;
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		if (getBehaviorType().equals(CHANGE_HEALTH))
			attributeType = AttributeType.HEALTH;
		arguments.add(attributeType);
		arguments.add((int) getValue());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		setTriggerOrAction();
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			setTextFieldValue(Integer.toString(((ChangeAttribute) getMyRule().getMyAction()).getMyValue()));
		}catch(Exception e){
		}
	}
}