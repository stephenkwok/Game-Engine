package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelNameEditor extends TextFieldWithButton {
	
	public TextFieldLevelNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> 
		((Level) getEditableElement()).setMyName(getTextFieldInput()));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(((Level) getEditableElement()).getName());	
	}

}
