package authoringenvironment.view;

import authoringenvironment.model.IAuthoringLevel;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PreviewUnitWithLevel extends PreviewUnitWithEditable {

	private static final String TEXT_FIELD_PROMPT_TEXT = "Enter Play Pos.";
	private static final Double TEXT_FIELD_WIDTH = 125.0;
	private TextField myTextField;

	public PreviewUnitWithLevel(IAuthoringLevel level, IEditingEnvironment environment) {
		super(level, environment);
		myTextField = new TextField();
		myTextField.setPrefWidth(TEXT_FIELD_WIDTH);
		myTextField.setPromptText(TEXT_FIELD_PROMPT_TEXT);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		getHBox().getChildren().add(myTextField);
	}

	public void updateLevelPlayPosition() {
		((Level) getEditable()).setPlayPosition(getLevelPlayPosition());
	}

	public int getLevelPlayPosition() {
		String textFieldInput = myTextField.getText().trim();
		return inputIsValid(textFieldInput) ? Integer.parseInt(textFieldInput) : -1;
	}

	public boolean inputIsValid(String input) {
		for (Character character : input.toCharArray()) {
			if (!Character.isDigit(character))
				return false;
		}
		return !input.isEmpty();
	}

}