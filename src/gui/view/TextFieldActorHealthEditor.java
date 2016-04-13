package gui.view;

import gameengine.model.Actor;

public class TextFieldActorHealthEditor extends TextFieldWithButton {

	public TextFieldActorHealthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).setMyHealth(Double.parseDouble(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}