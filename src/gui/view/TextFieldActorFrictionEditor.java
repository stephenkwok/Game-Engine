package gui.view;

import gameengine.model.IAuthoringActor;

public class TextFieldActorFrictionEditor extends TextFieldWithButton {

	public TextFieldActorFrictionEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((IAuthoringActor) getEditableElement()).setMyFriction(Double.parseDouble(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
