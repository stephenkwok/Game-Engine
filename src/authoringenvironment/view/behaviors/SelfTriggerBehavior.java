package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Triggers.Trigger;

public class SelfTriggerBehavior extends LabelBehavior {
	private Trigger myTrigger;
	private IAuthoringActor myActor;

	public SelfTriggerBehavior(ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
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
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return true;
	}

	@Override
	public void updateValueBasedOnEditable() {
		// do nothing
	}

}