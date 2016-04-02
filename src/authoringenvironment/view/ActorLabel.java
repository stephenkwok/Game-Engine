package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IActor;

public class ActorLabel extends ClickableLabel {

	Controller controller;
	
	public ActorLabel(IEditableGameElement editable, Controller controller) {
		super(editable);
		this.controller = controller;
	}

	@Override
	protected void reactToMouseClicked() {
		IActor myActor = (IActor) getEditable();
		controller.goToActorEditing(myActor);

	}

}
