package authoringenvironment.view.behaviors;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.ITrigger;

public class ClickBehavior extends LabelBehavior {
	private ITrigger myTrigger;
	private IAuthoringActor myActor;
	
	public ClickBehavior(IAuthoringActor myActor, String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
		this.myActor = myActor;
	}

	@Override
	void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		//add ITrigger 
		System.out.println(myTrigger);
	}

}
