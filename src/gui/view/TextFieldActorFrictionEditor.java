package gui.view;

import gameengine.model.Actor;

public class TextFieldActorFrictionEditor extends TextFieldWithButton {

	public TextFieldActorFrictionEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Actor) getEditableElement()).setMyFriction(Double.parseDouble(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}

}
