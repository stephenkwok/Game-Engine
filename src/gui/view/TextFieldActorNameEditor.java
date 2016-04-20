package gui.view;

import authoringenvironment.model.IAuthoringActor;

public class TextFieldActorNameEditor extends TextFieldWithButton {

	public TextFieldActorNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> {((IAuthoringActor) getEditableElement()).setMyName(getTextFieldInput());
			notifyController((IAuthoringActor) getEditableElement());
			});
	}
	/**
	 * Sets the textfield's value to reflect the current actor's name.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(((IAuthoringActor) getEditableElement()).getMyName());
	}

}
