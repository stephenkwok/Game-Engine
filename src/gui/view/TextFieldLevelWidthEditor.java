package gui.view;

import gameengine.controller.Level;

public class TextFieldLevelWidthEditor extends TextFieldWithButton {

	private Level myLevel;
	
	public TextFieldLevelWidthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		myLevel = (Level) getEditableElement();
		setButtonAction(e -> myLevel.setWidth(Double.valueOf(getTextFieldInput())));
	}

}
