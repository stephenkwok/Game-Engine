package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelNameEditor extends TextFieldWithButton {

	private Level myLevel;
	
	public TextFieldLevelNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		myLevel = (Level) getEditableElement();
		setButtonAction(e -> myLevel.setName(getTextFieldInput()));
	}

}
