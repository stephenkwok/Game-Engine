package gui.view;

import gameengine.controller.GameInfo;

/**
 * This class extends the TextFieldWithButton class and allows the author to set the game's name
 * 
 * @author Stephen
 *
 */

public class TextFieldGameNameEditor extends TextFieldWithButton{
	
	public TextFieldGameNameEditor(String labelText, String prompt, Double textFieldWidth) {
		super(labelText, prompt, textFieldWidth);
		setButtonAction(e -> ((GameInfo) getEditableElement()).setMyName(getTextFieldInput()));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}
}
