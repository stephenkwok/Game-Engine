package authoringenvironment.view;


import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class HBoxWithLevel extends HBoxWithEditable {

	private static final String TEXT_FIELD_PROMPT_TEXT = "Enter Play Position";
	private static final Double TEXT_FIELD_WIDTH = 125.0;
	private TextField myTextField;
	
	public HBoxWithLevel(IEditableGameElement editable, IEditingEnvironment environment) {
		super(editable, environment);
		myTextField = new TextField();
		myTextField.setPrefWidth(TEXT_FIELD_WIDTH);
		myTextField.setPromptText(TEXT_FIELD_PROMPT_TEXT);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
		getHBox().getChildren().add(myTextField);
	}
	
	public int getLevelPlayPosition() {
		String textFieldInput = myTextField.getText();
		return textFieldInput.isEmpty() ? -1 : Integer.parseInt(textFieldInput);
	}

}
