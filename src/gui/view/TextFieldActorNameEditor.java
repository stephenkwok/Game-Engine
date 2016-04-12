package gui.view;

import gameengine.model.Actor;

public class TextFieldActorNameEditor extends TextFieldWithButton {

	public TextFieldActorNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).setMyName(getTextFieldInput()));
	}

}
