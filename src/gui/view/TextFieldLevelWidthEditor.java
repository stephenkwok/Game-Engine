package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelWidthEditor extends TextFieldWithButton {
	
	public TextFieldLevelWidthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Level) getEditableElement()).setMyWidth(Double.valueOf(getTextFieldInput())));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(Double.toString(((Level) getEditableElement()).getWidth()));	
	}

}
