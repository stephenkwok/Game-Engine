package authoringenvironment.view.behaviors;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;

public class SelfTriggerBehavior extends LabelBehavior {
	private ITrigger myTrigger;
	private IAuthoringActor myActor;
	
	public SelfTriggerBehavior(IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		System.out.println(myTrigger);
	}

	@Override
	public IAction getAction() {
		return null;
	}

	@Override
	public ITrigger getTrigger() {
		return this.myTrigger;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

}
