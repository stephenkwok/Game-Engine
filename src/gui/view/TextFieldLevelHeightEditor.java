package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelHeightEditor extends TextFieldWithButton {

	private Level myLevel;
	
	public TextFieldLevelHeightEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		myLevel = (Level) getEditableElement();
		setButtonAction(e -> myLevel.setHeight(Double.valueOf(getTextFieldInput())));
	}
}
