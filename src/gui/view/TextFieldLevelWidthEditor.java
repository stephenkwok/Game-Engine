package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelWidthEditor extends TextFieldWithButton {
	
	public TextFieldLevelWidthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Level) getEditableElement()).setWidth(Double.valueOf(getTextFieldInput())));
	}

}
