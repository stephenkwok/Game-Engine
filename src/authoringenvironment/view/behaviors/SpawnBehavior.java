package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorGroup;
import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Actions.Spawn;

public class SpawnBehavior extends SelectActorBehavior {
	private IAction myAction;
	
	public SpawnBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 
			IAuthoringActor myActor, List<IAuthoringActor> myActors, Map<Integer, ActorGroup> myActorGroups) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors, myActorGroups);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(getOtherActor());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((IEditableGameElement) (((Spawn) getMyRule().getMyAction()).getMySpawnedActor()));
		}catch(Exception e){
		}
	}

}
