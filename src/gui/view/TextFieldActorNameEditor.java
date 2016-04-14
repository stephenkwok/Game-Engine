package gui.view;

import gameengine.model.IAuthoringActor;

public class TextFieldActorNameEditor extends TextFieldWithButton {

	public TextFieldActorNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((IAuthoringActor) getEditableElement()).setMyName(getTextFieldInput()));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(((IAuthoringActor) getEditableElement()).getMyName());
	}

}
