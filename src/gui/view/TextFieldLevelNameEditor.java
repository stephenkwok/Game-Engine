package gui.view;

import gameengine.controller.Level;

/**
 * 
 * This class extends the TextFieldWithButton class and enables the author to set the name of a given 
 * level by entering the name in a text field and clicking a button
 * 
 * @author Stephen
 *
 */

public class TextFieldLevelNameEditor extends TextFieldWithButton {
	
	public TextFieldLevelNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> 
		((Level) getEditableElement()).setName(getTextFieldInput()));
	}

}
