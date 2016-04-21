package authoringenvironment.view.behaviors;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IAction;
import gameengine.model.ITrigger;

public class SelfActionBehavior extends LabelBehavior {
	private IAction myAction;
	private IAuthoringActor myActor;
	
	public SelfActionBehavior(IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		System.out.println(myAction);
	}

	@Override
	public IAction getAction() {
		return this.myAction;
	}

	@Override
	public ITrigger getTrigger() {
		return null;
	}

}
