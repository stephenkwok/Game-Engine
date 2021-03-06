package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IAction;

public class SelfActionBehavior extends LabelBehavior {
	private IAction myAction;
	private IAuthoringActor myActor;

	public SelfActionBehavior(ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
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
		arguments.add(myActor);
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	public void updateValueBasedOnEditable() {
		
	}

}