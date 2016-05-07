package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IRule;
import gameengine.model.Triggers.Trigger;
import gameengine.model.Triggers.TickTrigger;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class TickBehavior extends DoubleBehavior {
	private Trigger myTrigger;

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
		}
	}
}
