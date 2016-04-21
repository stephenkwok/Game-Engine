package authoringenvironment.view.behaviors;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;

public class SelfActionBehavior extends LabelBehavior {
	private IAction myAction;
	private IAuthoringActor myActor;
	
	public SelfActionBehavior(ActorRule myActorRule, IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		addAction(this, myAction);
		System.out.println(myAction);
	}

	@Override
	public void addNodeObserver(Observer observer) {

	}

}
