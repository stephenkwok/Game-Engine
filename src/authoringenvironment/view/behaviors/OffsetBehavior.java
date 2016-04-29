package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Actions.GlidingAction;
//gliding
public class OffsetBehavior extends DoubleBehavior {
	private IAuthoringActor myActor;
	private IAction myAction;
	
	public OffsetBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((myActor));
		arguments.add(getValue());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		setTriggerOrAction();
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			setTextFieldValue(Double.toString((((GlidingAction) getMyRule().getMyAction()).getGlideOffset())));
		}catch(Exception e){
		}
	}

}