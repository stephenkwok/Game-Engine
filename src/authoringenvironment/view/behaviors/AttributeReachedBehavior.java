package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.AttributeType;
import gameengine.model.IRule;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.Trigger;

public class AttributeReachedBehavior extends DoubleBehavior {

	private static final String ATTRIBUTE_RESOURCES = "attributeResources";
	private static final String CHANGE_HEALTH = "ChangeHealth";
	private IAuthoringActor myActor;
	private Trigger myTrigger;
	private ResourceBundle myBundle;

	public AttributeReachedBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
		myBundle = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		arguments.add(AttributeType.valueOf(myBundle.getString(getBehaviorType())));
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