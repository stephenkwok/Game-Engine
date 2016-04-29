package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorGroup;
import authoringenvironment.view.ActorRule;
import gameengine.model.IRule;
import gameengine.model.Actions.ChangeAttribute;
import gameengine.model.Triggers.CollisionTrigger;
import gameengine.model.Triggers.ITrigger;

public class CollisionBehavior extends SelectActorBehavior {
	private ITrigger myTrigger;

	public CollisionBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors);
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(getOtherActor());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return true;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((IEditableGameElement) (((CollisionTrigger) getMyRule().getMyTrigger()).getMyCollisionActor()));
		}catch(Exception e){
		}
	}
}
