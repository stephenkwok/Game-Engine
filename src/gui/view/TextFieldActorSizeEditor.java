package gui.view;

import gameengine.model.Actor;

public class TextFieldActorSizeEditor extends TextFieldWithButton {

	public TextFieldActorSizeEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).setSize(Double.parseDouble(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}

}
