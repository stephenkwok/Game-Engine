package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IActor;

public class LabelActor extends LabelClickable {

	Controller controller;
	
	public LabelActor(IEditableGameElement editable, Controller controller) {
		super(editable);
		this.controller = controller;
	}

	@Override
	protected void reactToMouseClicked() {
		IActor myActor = (IActor) getEditable();
		controller.goToActorEditing(myActor);

	}

}
