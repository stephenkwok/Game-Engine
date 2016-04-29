package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.AttributeType;
import gameengine.model.IRule;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;

public class AttributeReachedBehavior extends DoubleBehavior {

	private static final String CHANGE_HEALTH = "ChangeHealth";
	private IAuthoringActor myActor;
	private ITrigger myTrigger;

	public AttributeReachedBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		if(getBehaviorType().equals(CHANGE_HEALTH)) arguments.add(AttributeType.HEALTH);
		else arguments.add(AttributeType.POINTS);
		arguments.add((int) getValue());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		setTriggerOrAction();
	}

	@Override
	public boolean isTrigger() {
		return true;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			setTextFieldValue(Integer.toString(((AttributeReached) getMyRule().getMyTrigger()).getMyValue()));
		}catch(Exception e){
		}
	}

}