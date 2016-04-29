package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;
import gameengine.model.IRule;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.TickTrigger;

public class TickBehavior extends DoubleBehavior {
	private ITrigger myTrigger;

	public TickBehavior(IRule myRule, ActorRule myActorRule, IAuthoringActor myActor, String behaviorType,
			ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getValue());
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
			setTextFieldValue(Integer.toString(((TickTrigger) getMyRule().getMyTrigger()).getMyInterval()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
