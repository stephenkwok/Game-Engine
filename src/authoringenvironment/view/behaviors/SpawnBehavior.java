package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorRule;

public class SpawnBehavior extends SelectEditableBehavior {

	public SpawnBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources,
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myActorRule, behaviorType, myResources, myActor, myActors);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createTriggerOrAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTriggerOrAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTrigger() {
		// TODO Auto-generated method stub
		return false;
	}

}
