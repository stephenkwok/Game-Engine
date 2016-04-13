package gui.view;

import gameengine.model.IAuthoringActor;

public class TextFieldActorSizeEditor extends TextFieldWithButton {

	public TextFieldActorSizeEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((IAuthoringActor) getEditableElement()).setSize(Double.parseDouble(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}

}
