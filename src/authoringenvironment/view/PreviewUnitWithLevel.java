// This entire file is part of my masterpiece
// Stephen Kwok

// This class is included in my masterpiece to showcase the extensibility of my PreviewUnitWithEditable
// class. I wanted to add functionality to the preview unit to allow users to input a "level number"
// for each level displayed, which would determine when in the game that level would be played. To do so,
// I simply create the additional nodes needed to support this feature, then add that node to the 
// hbox contained in the parent class. This demonstrates the extensibility of my code since you can
// see how we can continue adding as many features as we wanted to the preview unit.

package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.Level;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * This class creates a preview unit for a created level and allows the author
 * to enter a level number representing where in the Game that Level will be played 
 * (A level number of 1 would indicate the Level should be played first, etc.)
 * 
 * @author Stephen
 *
 */
public class PreviewUnitWithLevel extends PreviewUnitWithEditable {

	private static final String TEXT_FIELD_PROMPT_TEXT = "Enter Level Number";
	private static final Double TEXT_FIELD_WIDTH = 125.0;
	private final TextField myTextField;

	/**
	 * Creates a preview unit for a Level
	 * @param editable: the IEditableGameElement the preview unit will be displaying
	 */
	public PreviewUnitWithLevel(IEditableGameElement editable) {
		super(editable);
		myTextField = new TextField();
		myTextField.setPrefWidth(TEXT_FIELD_WIDTH);
		myTextField.setPromptText(TEXT_FIELD_PROMPT_TEXT);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		getHBox().getChildren().add(myTextField);
	}

	/**
	 * Sets a Level's play position to the value entered by the author
	 */
	public void updateLevelPlayPosition() {
		((Level) getEditable()).setPlayPosition(getLevelPlayPosition());
	}

	/**
	 * Retrieves author's input for a Level's play position
	 * @return: the play position assigned to a Level by the author
	 */
	public int getLevelPlayPosition() {
		String textFieldInput = myTextField.getText().trim();
		return inputIsValid(textFieldInput) ? Integer.parseInt(textFieldInput) : -1;
	}

	/**
	 * Checks whether the author has entered valid input
	 * 
	 * @param input: the text the author entered into the preview unit's text field
	 * @return true if the author entered valid input; false otherwise
	 */
	private boolean inputIsValid(String input) {
		for (Character character : input.toCharArray()) {
			if (!Character.isDigit(character))
				return false;
		}
		return !input.isEmpty();
	}

}
