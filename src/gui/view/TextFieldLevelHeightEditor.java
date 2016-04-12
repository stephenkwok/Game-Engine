package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelHeightEditor extends TextFieldWithButton {

	public TextFieldLevelHeightEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Level) getEditableElement()).setMyHeight(Double.valueOf(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(Double.toString(((Level) getEditableElement()).getHeight()));	
	}
}
