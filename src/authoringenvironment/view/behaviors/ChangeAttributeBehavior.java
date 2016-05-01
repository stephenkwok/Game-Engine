package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.AttributeType;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Actions.ChangeAttribute;

public class ChangeAttributeBehavior extends DoubleBehavior {
	private static final String CHANGE_HEALTH = "ChangeHealth";
    private static final String ATTRIBUTE_RESOURCES = "attributeResources";
    private static final String CHANGE_ATTRIBUTE_ACTIONS = "ChangeAttributeActions";
    private IAuthoringActor myActor;
	private IAction myAction;
	private AttributeType attributeType;
    private ResourceBundle attributeBundle;

	public ChangeAttributeBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
		this.attributeType = AttributeType.POINTS;
        attributeBundle = ResourceBundle.getBundle(ATTRIBUTE_RESOURCES);
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
        if(attributeBundle.getString(CHANGE_ATTRIBUTE_ACTIONS).contains(getBehaviorType())){
            attributeType = AttributeType.valueOf(attributeBundle.getString(getBehaviorType()));
        }
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