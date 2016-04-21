package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorRule;
import gameengine.model.IAction;

public class NOTUSEDSelectActorBehavior extends IEditableGameElementBehavior{
	private IAction myAction;
	private List<IAuthoringActor> myActors;
	private IAuthoringActor selectedActor;
	
	public NOTUSEDSelectActorBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources, List<IAuthoringActor> myActors) {
		super(myActorRule, behaviorType,myResources);
		this.myActors = myActors;
		setButtonAction(e -> {
			selectedActor = (IAuthoringActor) getComboBox().getValue();
			List<Object> arguments = new ArrayList<>();
			arguments.add(selectedActor);
			myAction = getActionFactory().createNewAction(behaviorType, arguments);
			addAction(this, myAction);
			System.out.println(myAction);
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
		getComboBox().setValue(selectedActor);
		
	}
}
