package gui.view;

import authoringenvironment.model.IAuthoringActor;

public class TextFieldActorSizeEditor extends TextFieldWithButton {

	public TextFieldActorSizeEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> {((IAuthoringActor) getEditableElement()).setSize(Double.parseDouble(getTextFieldInput()));
		setChanged(); notifyObservers((IAuthoringActor) getEditableElement());});
	}
	/**
	 * Sets the textfield's value to reflect the current actor's size.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(Double.toString(((IAuthoringActor) getEditableElement()).getSize()));
	}

}
