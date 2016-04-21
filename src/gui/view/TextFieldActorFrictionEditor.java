package gui.view;

import authoringenvironment.model.IAuthoringActor;

public class TextFieldActorFrictionEditor extends TextFieldWithButton {

	public TextFieldActorFrictionEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((IAuthoringActor) getEditableElement()).setFriction(Double.parseDouble(getTextFieldInput())));
	}
	/**
	 * Sets the textfield's value to reflect the current actor's friction.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(Double.toString(((IAuthoringActor) getEditableElement()).getFriction()));
	}

}
