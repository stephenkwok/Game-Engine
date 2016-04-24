package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorRule;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;

public class CollisionBehavior extends SelectEditableBehavior {
	private List<IAuthoringActor> myActors;
	private IAuthoringActor otherActor;
	private ITrigger myTrigger;
	private IAuthoringActor myActor;
	
	public CollisionBehavior(ActorRule myActorRule, String behaviorType, IAuthoringActor myActor, ResourceBundle myResources, List<IAuthoringActor> myActors) {
		super(myActorRule, behaviorType, myResources);
		this.myActors = myActors;
		this.myActor = myActor;
		setButtonAction(e -> {
			this.otherActor = (IAuthoringActor) getComboBox().getValue();
			createTriggerOrAction();
			setTriggerOrAction();
		});
	}

	@Override
	List<IEditableGameElement> getOptionsList() {
		List<IEditableGameElement> toReturn = new ArrayList<>();
		for(IAuthoringActor actor: myActors){
			toReturn.add(actor);
		}
		return toReturn;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(otherActor);
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add((IPlayActor) myActor);
		arguments.add((IPlayActor) otherActor);
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return true;
	}
}
