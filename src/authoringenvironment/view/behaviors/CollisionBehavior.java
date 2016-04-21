package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.Actor;
import gameengine.model.IAction;
import gameengine.model.ITrigger;

public class CollisionBehavior extends IEditableGameElementBehavior {
	private List<IAuthoringActor> myActors;
	private IAuthoringActor otherActor;
	private ITrigger myTrigger;
	
	public CollisionBehavior(String behaviorType, IAuthoringActor myActor, ResourceBundle myResources, List<IAuthoringActor> myActors) {
		super(behaviorType, myResources);
		this.myActors = myActors;
		setButtonAction(e -> {
			this.otherActor = (IAuthoringActor) getComboBox().getValue();
			List<Object> arguments = new ArrayList<>();
			arguments.add((Actor) myActor);
			arguments.add((Actor) otherActor);
			myTrigger = getTriggerFactory().createNewTrigger(behaviorType, arguments);
			System.out.println(myTrigger);
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
	public IAction getAction() {
		return null;
	}

	@Override
	public ITrigger getTrigger() {
		return this.myTrigger;
	}

}
