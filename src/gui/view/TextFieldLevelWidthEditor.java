package gui.view;

import gameengine.controller.Level;

/**
 * TextField to edit the level width.
 * @author amyzhao
 *
 */
public class TextFieldLevelWidthEditor extends TextFieldWithButton {
	
	/**
	 * Constructs a TextField to edit a level's width with a given prompt and width, and a label to the left and a "GO" button to the right.
	 * @param labelText: text for the label.
	 * @param promptText: prompt text for the textfield.
	 * @param textFieldWidth: width of the textfield.
	 */
	public TextFieldLevelWidthEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> ((Level) getEditableElement()).setMyWidth(Double.valueOf(getTextFieldInput())));
	}

	/**
	 * Sets the textfield's value to reflect the current level's width.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(Double.toString(((Level) getEditableElement()).getMyWidth()));
	}

}
