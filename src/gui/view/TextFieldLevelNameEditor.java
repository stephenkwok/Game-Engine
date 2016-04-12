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
	
	/**
	 * Constructs a TextField to edit a level's name with a given prompt and width, and a label to the left and a 
	 * "GO" button to the right.
	 * @param labelText: text for the label.
	 * @param promptText: prompt text for the textfield.
	 * @param textFieldWidth: width of the textfield.
	 */
	public TextFieldLevelNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> 
		((Level) getEditableElement()).setMyName(getTextFieldInput()));
	}

	/**
	 * Sets the textfield's value to reflect the current level's name.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(((Level) getEditableElement()).getMyName());	
	}

}
