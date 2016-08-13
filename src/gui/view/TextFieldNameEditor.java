package gui.view;

public class TextFieldNameEditor extends TextFieldWithButton {

	/**
	 * Constructs a TextField to edit a level's name with a given prompt and
	 * width, and a label to the left and a "GO" button to the right.
	 * 
	 * @param labelText:
	 *            text for the label.
	 * @param promptText:
	 *            prompt text for the textfield.
	 * @param textFieldWidth:
	 *            width of the textfield.
	 */
	public TextFieldNameEditor(String labelText, String promptText, Double textFieldWidth) {
		super(labelText, promptText, textFieldWidth);
		setButtonAction(e -> (getEditableElement()).setName(getTextFieldInput()));
	}

	/**
	 * Sets the textfield's value to reflect the current level's name.
	 */
	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue((getEditableElement()).getName());
	}
	
}
