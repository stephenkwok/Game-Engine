package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;

public class OffsetBehavior extends DoubleBehavior {
	private IAuthoringActor myActor;
	private IAction myAction;
	
	public OffsetBehavior(ActorRule myActorRule, IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
		System.out.println("past constructor");
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

}
