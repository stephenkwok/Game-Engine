package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.ILevel;

public class LevelLabel extends ClickableLabel {

	Controller controller;
	
	public LevelLabel(IEditableGameElement editable, Controller controller) {
		super(editable);
		this.controller = controller;
	}

	@Override
	protected void reactToMouseClicked() {
		ILevel myLevel = (ILevel) getEditable();
		controller.goToLevelEditing(myLevel);
	}


}
