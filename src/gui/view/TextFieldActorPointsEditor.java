package gui.view;

import gameengine.model.Actor;

public class TextFieldActorPointsEditor extends TextFieldWithButton {

	public TextFieldActorPointsEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> {}); //TODO
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
