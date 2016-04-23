package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;

public class NOTUSEDSelectActorBehavior extends IEditableGameElementBehavior{
	private IAction myAction;
	private List<IAuthoringActor> myActors;
	private IAuthoringActor selectedActor;
	
	public NOTUSEDSelectActorBehavior(String behaviorType, ResourceBundle myResources, List<IAuthoringActor> myActors) {
		super(behaviorType,myResources);
		this.myActors = myActors;
		setButtonAction(e -> {
			selectedActor = (IAuthoringActor) getComboBox().getValue();
			List<Object> arguments = new ArrayList<>();
			arguments.add(selectedActor);
			myAction = getActionFactory().createNewAction(behaviorType, arguments);
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
	public IAction getAction() {
		return this.myAction;
	}

	@Override
	public ITrigger getTrigger() {
		return null;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(selectedActor);
		
	}
}
